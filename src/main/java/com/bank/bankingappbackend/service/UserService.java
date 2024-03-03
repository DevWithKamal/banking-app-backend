package com.bank.bankingappbackend.service;

import com.bank.bankingappbackend.DTOs.UserRequestDTO;
import com.bank.bankingappbackend.DTOs.UserResponseDTO;

public interface UserService {

    public UserResponseDTO createUserRegistration(UserRequestDTO userRequestDTO);

    public UserResponseDTO getUserRegistration(Integer userId);



}
