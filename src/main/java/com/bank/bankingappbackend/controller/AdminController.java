package com.bank.bankingappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(value = "*")
public class AdminController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello(){

        return  ResponseEntity.ok("Hi Admin");
    }
}
