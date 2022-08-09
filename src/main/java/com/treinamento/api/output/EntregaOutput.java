package com.treinamento.api.output;

import com.treinamento.domain.entity.Entrega;
import com.treinamento.domain.entity.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaOutput {

    private Long id;
    private String nomeCliente;
    private DestinatarioOutput destinatarioOutput;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    public EntregaOutput(Entrega entrega) {
        this.id = entrega.getId();
        this.nomeCliente = entrega.getCliente().getNome();
        this.destinatarioOutput = new DestinatarioOutput(entrega.getDestinatario());
        this.taxa = entrega.getTaxa();
        this.status = entrega.getStatus();
        this.dataPedido = entrega.getDataPedido();
        this.dataFinalizacao = entrega.getDataFinalizacao();
    }
}
