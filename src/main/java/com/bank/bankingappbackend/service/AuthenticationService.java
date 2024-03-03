package com.bank.bankingappbackend.service;

import com.bank.bankingappbackend.DTOs.JWTAuthenticationResponse;
import com.bank.bankingappbackend.DTOs.RefreshTokenRequest;
import com.bank.bankingappbackend.DTOs.SignInRequest;
import com.bank.bankingappbackend.DTOs.SignUpRequest;
import com.bank.bankingappbackend.entity.User;

public interface AuthenticationService {

    public User signUp(SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn(SignInRequest signInRequest);

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
