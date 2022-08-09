package com.treinamento.api.output;

import com.treinamento.domain.entity.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaOutput {

    private Long id;
    private ClienteIdOutput cliente;
    private DestinatarioOutput destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
