package com.ramon.simplifiedpaymentapi.controllers;

import com.ramon.simplifiedpaymentapi.domain.user.User;
import com.ramon.simplifiedpaymentapi.dtos.UserDTO;
import com.ramon.simplifiedpaymentapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @PostMapping
    public ResponseEntity<User>createUser(UserDTO userDTO) {
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<User>>getAllUsers() {
        List<User> users =  userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

}
