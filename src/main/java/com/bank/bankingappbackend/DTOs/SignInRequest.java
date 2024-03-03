package com.bank.bankingappbackend.DTOs;

import lombok.Data;

@Data
public class SignInRequest {


    private String email;

    private  String password;
}
