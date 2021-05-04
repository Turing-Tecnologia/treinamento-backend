package com.turingtecnologia.albatroz.backendalbatroz.exceptions.handler;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .title("Resource not found")
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .detail(rnfException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotAcceptableException.class)
    public ResponseEntity<?> handleResourceNotAcceptableException(ResourceNotAcceptableException rnaException) {
        ResourceNotAcceptableDetails rnaDetails = ResourceNotAcceptableDetails.Builder
                .newBuilder()
                .title("Dados não preenchidos")
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .detail(rnaException.getMessage())
                .build();
        return new ResponseEntity<>(rnaDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ResourceArgumentNotValid resourceArgumentNotValid = ResourceArgumentNotValid.Builder
            .newBuilder()
            .title("Field Validation Error")
            .timeStamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .field(fields)
            .fieldMessage(fieldMessage)
            .build();
        return new ResponseEntity<>(resourceArgumentNotValid, HttpStatus.BAD_REQUEST);
    }
}
