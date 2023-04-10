package com.example.labs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Illegal arguments")
public class IllegalArgumentsException extends Exception{

    public IllegalArgumentsException(String message){
        super(message);
    }
}
