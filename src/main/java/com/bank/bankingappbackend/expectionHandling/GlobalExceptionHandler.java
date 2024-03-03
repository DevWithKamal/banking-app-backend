package com.bank.bankingappbackend.expectionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(EmailAlreadyExistException.class)
   public ResponseEntity<ApiResponse> emailAlreadyExist(EmailAlreadyExistException emailAlreadyExistException, WebRequest webRequest)
   {
    ApiResponse apiResponse=new ApiResponse(HttpStatus.BAD_REQUEST,"Email Already Exists",null);

    return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
   }


}
