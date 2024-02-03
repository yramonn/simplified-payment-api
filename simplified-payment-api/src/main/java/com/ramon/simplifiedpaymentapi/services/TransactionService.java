package com.ramon.simplifiedpaymentapi.services;

import com.ramon.simplifiedpaymentapi.domain.transaction.Transaction;
import com.ramon.simplifiedpaymentapi.domain.user.User;
import com.ramon.simplifiedpaymentapi.dtos.TransactionDTO;
import com.ramon.simplifiedpaymentapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    private UserService userService;

    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

        boolean isAuthorized = authorizeTransaction(sender, transactionDTO.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizado");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setCreated_at(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }
    public boolean authorizeTransaction(User sender, BigDecimal amount) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/9b89b419-a2f7-4885-aa86-5ddcea24d520", Map.class);

                if(authorizationResponse.getStatusCode()== HttpStatus.OK) {
                    String message = (String) authorizationResponse.getBody().get("message");
                    return "Autorizado".equalsIgnoreCase(message);
                } else return false;
    }
}
