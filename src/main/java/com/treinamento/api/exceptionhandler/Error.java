package com.treinamento.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Error {

    private Integer status;
    private OffsetDateTime dateHour;
    private String title;
    private List<InputError> input;


}
