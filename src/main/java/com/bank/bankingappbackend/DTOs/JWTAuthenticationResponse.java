package com.bank.bankingappbackend.DTOs;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {

    private String token;

    private  String refreshToken;
}
