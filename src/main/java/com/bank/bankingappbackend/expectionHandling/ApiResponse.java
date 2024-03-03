package com.bank.bankingappbackend.expectionHandling;

import lombok.Data;
import org.springframework.http.HttpStatus;




@Data
public class ApiResponse {

    private String message;
    private Integer status;
    private  Object data;


    public ApiResponse(HttpStatus status,String message,  Object data) {
        this.message = message;
        this.status = status.value();
        this.data = data;
    }
}
