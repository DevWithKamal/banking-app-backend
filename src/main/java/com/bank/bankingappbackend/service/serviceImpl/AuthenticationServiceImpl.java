package com.bank.bankingappbackend.service.serviceImpl;

import com.bank.bankingappbackend.DTOs.JWTAuthenticationResponse;
import com.bank.bankingappbackend.DTOs.RefreshTokenRequest;
import com.bank.bankingappbackend.DTOs.SignInRequest;
import com.bank.bankingappbackend.DTOs.SignUpRequest;
import com.bank.bankingappbackend.Enum.Role;
import com.bank.bankingappbackend.entity.User;
import com.bank.bankingappbackend.repo.UserRepository;
import com.bank.bankingappbackend.service.AuthenticationService;
import com.bank.bankingappbackend.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
       user.setUserName(signUpRequest.getFirstName());

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return user;
    }

    @Override
    public JWTAuthenticationResponse signIn(SignInRequest signInRequest) {


        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (signInRequest.getEmail(), signInRequest.getPassword()));

        }catch (Exception e){
            e.printStackTrace();
        }
        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new
                        IllegalArgumentException("Invalid mail or password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;

    }
    @Override
    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
    {
        String userEmail=jwtService.extractUserName(refreshTokenRequest.getToken());
        User user=userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){

            var jwt=jwtService.generateToken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse=new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;

        }

        return  null;
    }
}
