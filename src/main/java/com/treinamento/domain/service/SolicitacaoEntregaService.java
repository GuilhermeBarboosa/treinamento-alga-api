package com.treinamento.domain.service;

import com.treinamento.domain.entity.Cliente;
import com.treinamento.domain.entity.Entrega;
import com.treinamento.domain.entity.StatusEntrega;
import com.treinamento.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

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
