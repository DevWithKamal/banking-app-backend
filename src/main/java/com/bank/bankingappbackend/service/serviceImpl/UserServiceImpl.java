package com.bank.bankingappbackend.service.serviceImpl;

import com.bank.bankingappbackend.DTOs.UserRequestDTO;
import com.bank.bankingappbackend.DTOs.UserResponseDTO;
import com.bank.bankingappbackend.entity.User;
import com.bank.bankingappbackend.expectionHandling.EmailAlreadyExistException;
import com.bank.bankingappbackend.repo.UserRepository;
import com.bank.bankingappbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserResponseDTO createUserRegistration(UserRequestDTO userRequestDTO) {


        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistException();
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userRequestDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserRegistration(Integer userId) {
        return null;
    }
}
