package com.treinamento.codiub.api.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Error {

    private Integer status;
    private LocalDateTime dateHour;
    private String title;
    private List<InputError> input;


}
