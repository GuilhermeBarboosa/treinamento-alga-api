package com.treinamento.api.output;


import com.treinamento.domain.entity.Destinatario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioOutput {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    public DestinatarioOutput(Destinatario cliente) {
        this.nome = cliente.getNome();
        this.logradouro = cliente.getLogradouro();
        this.numero = cliente.getNumero();
        this.complemento = cliente.getComplemento();
        this.bairro = cliente.getBairro();
    }
}
