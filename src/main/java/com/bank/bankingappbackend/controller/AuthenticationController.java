package com.bank.bankingappbackend.controller;


import com.bank.bankingappbackend.DTOs.JWTAuthenticationResponse;
import com.bank.bankingappbackend.DTOs.RefreshTokenRequest;
import com.bank.bankingappbackend.DTOs.SignInRequest;
import com.bank.bankingappbackend.DTOs.SignUpRequest;
import com.bank.bankingappbackend.entity.User;
import com.bank.bankingappbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(value = "*")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping(value = "/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JWTAuthenticationResponse> refreshTokenRequest(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
