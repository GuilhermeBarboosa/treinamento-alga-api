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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<InputError> errorInputs = getInputErrors(ex);

        String title = "Campo invalido";
        Error error =  construtorError(status, title);
        error.setInput(errorInputs);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private List<InputError> getInputErrors(MethodArgumentNotValidException ex) {
        List<InputError> errorInputs = new ArrayList();
        for (ObjectError errorType : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) errorType).getField();
            String message = messageSource.getMessage(errorType, LocaleContextHolder.getLocale());
            errorInputs.add(new InputError(name, message));
        }
        return errorInputs;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String title = ex.getMessage();
        Error error =  construtorError(status, title);

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private Error construtorError(HttpStatus status, String title) {
        Error error = new Error();
        error.setStatus(status.value());
        error.setDateHour(OffsetDateTime.now());
        error.setTitle(title);
        return error;
    }

}
