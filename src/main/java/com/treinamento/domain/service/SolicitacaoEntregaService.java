package com.treinamento.domain.service;

import com.treinamento.domain.exception.DomainException;
import com.treinamento.domain.model.Cliente;
import com.treinamento.domain.model.Entrega;
import com.treinamento.domain.model.StatusEntrega;
import com.treinamento.domain.repository.ClienteRepository;
import com.treinamento.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    private CrudClienteService crudClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = crudClienteService.buscarClienteSolicitacao(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);

    }


}
