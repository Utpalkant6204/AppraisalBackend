package com.utpal.AppraisalStudy.Exceptions;

import com.utpal.AppraisalStudy.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setStatus((long)HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ExceptionDTO> handleSignDuplicates(UserAlreadyExists ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setStatus((long)HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);
    }

    // Add more exception handlers if needed for other custom exceptions
}

