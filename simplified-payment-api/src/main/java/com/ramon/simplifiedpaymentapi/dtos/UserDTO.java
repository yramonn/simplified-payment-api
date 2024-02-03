package com.ramon.simplifiedpaymentapi.dtos;

import com.ramon.simplifiedpaymentapi.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firsName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {

}
