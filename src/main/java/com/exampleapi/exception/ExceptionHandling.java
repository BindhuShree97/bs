package com.exampleapi.exception;

import com.exampleapi.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> resourceNotFoundException(ResourceNotFound r, WebRequest req){

        ErrorDto dto=new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(r.getMessage());
        dto.setRequest(req.getDescription(false));

        return new ResponseEntity<>(dto , HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> resourceNotFoundException(Exception r, WebRequest req){

        ErrorDto dto=new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(r.getMessage());
        dto.setRequest(req.getDescription(false));

        return new ResponseEntity<>(dto , HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

}
