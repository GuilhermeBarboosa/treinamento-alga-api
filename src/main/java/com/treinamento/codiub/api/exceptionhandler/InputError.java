package com.treinamento.codiub.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InputError {

    private String name;
    private String message;

}
