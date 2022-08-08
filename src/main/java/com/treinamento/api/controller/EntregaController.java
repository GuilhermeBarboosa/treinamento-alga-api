package com.treinamento.api.controller;

import com.treinamento.domain.model.Entrega;
import com.treinamento.domain.repository.EntregaRepository;
import com.treinamento.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody @Valid Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar(){
        return entregaRepository.findAll();
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<Entrega> procurarEntrega(@PathVariable Long entregaId){
        Optional<Entrega> entrega = entregaRepository.findById(entregaId);
        if(entrega.isPresent()){
            return ResponseEntity.ok(entrega.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
