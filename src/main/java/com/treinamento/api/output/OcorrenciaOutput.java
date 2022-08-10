package com.treinamento.api.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaOutput {

	private String descricao;
	private EntregaOutput entrega;
	private OffsetDateTime data;
	
}
