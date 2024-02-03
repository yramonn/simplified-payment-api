package com.ramon.simplifiedpaymentapi.services;

import com.ramon.simplifiedpaymentapi.domain.user.User;
import com.ramon.simplifiedpaymentapi.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/9b89b419-a2f7-4885-aa86-5ddcea24d520", notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK))
            System.out.println("Erro ao enviar notificação");
        throw new Exception("Serviço de notificação está fora do ar!");


    }
}
