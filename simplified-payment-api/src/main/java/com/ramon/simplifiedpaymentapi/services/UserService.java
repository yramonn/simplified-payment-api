package com.ramon.simplifiedpaymentapi.services;

import com.ramon.simplifiedpaymentapi.domain.user.User;
import com.ramon.simplifiedpaymentapi.domain.user.UserType;
import com.ramon.simplifiedpaymentapi.dtos.UserDTO;
import com.ramon.simplifiedpaymentapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.COMPANY) {
            throw new Exception(" Usuário do tipo logista não pode efetuar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Usuário não tem saldo");
        }
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }


    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        saveUser(newUser);
        return newUser ;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
