package com.treinamento.api.exceptionhandler;

import com.treinamento.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<InputError> errorInputs = new ArrayList();
       for (ObjectError errorType : ex.getBindingResult().getAllErrors()){
           String name = ((FieldError) errorType).getField();
           String message = messageSource.getMessage(errorType, LocaleContextHolder.getLocale());
           errorInputs.add(new InputError(name, message));
       }

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateHour(LocalDateTime.now());
        error.setTitle("Input invalid");
        error.setInput(errorInputs);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateHour(LocalDateTime.now());
        error.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
