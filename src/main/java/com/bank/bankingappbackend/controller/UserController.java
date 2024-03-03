package com.bank.bankingappbackend.controller;

import com.bank.bankingappbackend.DTOs.UserRequestDTO;
import com.bank.bankingappbackend.DTOs.UserResponseDTO;
import com.bank.bankingappbackend.expectionHandling.ApiResponse;
import com.bank.bankingappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/createRegistration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse> createRegistration(@RequestBody UserRequestDTO userRequestDTO) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "User Registered SuccessFully", userService.createUserRegistration(userRequestDTO));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        try {
            return "Hi  User";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
