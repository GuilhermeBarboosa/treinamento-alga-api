package com.treinamento.api.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
