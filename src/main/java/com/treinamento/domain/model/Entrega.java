package com.treinamento.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.treinamento.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter

@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @ManyToOne
    @NotNull
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;
}
