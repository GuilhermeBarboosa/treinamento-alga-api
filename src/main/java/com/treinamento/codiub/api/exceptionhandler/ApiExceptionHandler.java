package com.treinamento.codiub.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<InputError> errorInputs = new ArrayList();
       for (ObjectError erro : ex.getBindingResult().getAllErrors()){
           String name = ((FieldError) erro).getField();
           String message = erro.getDefaultMessage();
           errorInputs.add(new InputError(name, message));
       }

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateHour(LocalDateTime.now());
        error.setTitle("Input invalid");

        return handleExceptionInternal(ex, error, headers, status, request);
    }
}
