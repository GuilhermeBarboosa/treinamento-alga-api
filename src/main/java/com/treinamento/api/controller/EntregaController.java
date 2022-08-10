package com.treinamento.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import com.treinamento.api.input.EntregaInput;
import com.treinamento.api.mapper.EntregaMapper;
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
import com.treinamento.domain.service.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {
    private EntregaService entregaService;
    private EntregaRepository entregaRepository;
    private EntregaMapper entregaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaOutput solicitar(@RequestBody @Valid EntregaInput entregaInput) {
        Entrega novaEntrega = entregaMapper.toConvert(entregaInput);
        return entregaMapper.toModel(entregaService.solicitar(novaEntrega));
    }

    @GetMapping
    public List<EntregaOutput> listar() {
        return entregaMapper.toListOutput(entregaRepository.findAll());
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaOutput> procurarEntrega(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega))
                ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/encerrar/{entregaId}")
    public ResponseEntity<Entrega> encerrarEntrega(@Valid
                                                   @PathVariable Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId).orElseThrow(() -> new DomainException("Entrega não emcontrada"));
        entrega.setId(entrega.getId());
        entrega.setDataFinalizacao(OffsetDateTime.now());
        entrega = entregaService.solicitar(entrega);
        return ResponseEntity.ok(entrega);
    }

}
