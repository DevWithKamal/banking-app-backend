package com.bank.bankingappbackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface JWTService {
    String generateToken(UserDetails userDetails);

    String extractUserName(String token);



    public boolean isTokenValid(String token,UserDetails userDetails);


    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
