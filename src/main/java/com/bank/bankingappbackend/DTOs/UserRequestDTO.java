package com.bank.bankingappbackend.DTOs;


import lombok.Data;

@Data
public class UserRequestDTO {

    private String email;

    private String password;

    private String userName;

    private String role;

}
