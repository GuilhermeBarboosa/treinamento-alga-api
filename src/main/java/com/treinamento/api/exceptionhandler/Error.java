package com.treinamento.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Error {

    private Integer status;
    private LocalDateTime dateHour;
    private String title;
    private List<InputError> input;


}
