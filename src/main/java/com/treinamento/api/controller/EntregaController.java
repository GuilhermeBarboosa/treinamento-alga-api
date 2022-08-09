package com.treinamento.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.api.output.EntregaOutput;
import com.treinamento.domain.exception.DomainException;
import com.treinamento.domain.entity.Entrega;
import com.treinamento.domain.repository.EntregaRepository;
import com.treinamento.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaRepository entregaRepository;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody @Valid Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaOutput> procurarEntrega(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> {

                        EntregaOutput entregaOutput = modelMapper.map(entrega, EntregaOutput.class);
//                    EntregaOutput entregaOutput = new EntregaOutput(entrega);
                    return ResponseEntity.ok(entregaOutput);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/encerrar/{entregaId}")
    public ResponseEntity<Entrega> encerrarEntrega(@Valid
                                                   @PathVariable Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId).orElseThrow(() -> new DomainException("Entrega não emcontrada"));
        entrega.setId(entrega.getId());
        entrega.setDataFinalizacao(OffsetDateTime.now());
        entrega = solicitacaoEntregaService.solicitar(entrega);
        return ResponseEntity.ok(entrega);
    }

}
