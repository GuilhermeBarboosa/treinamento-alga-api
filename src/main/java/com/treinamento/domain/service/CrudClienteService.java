package com.treinamento.domain.service;

import com.treinamento.domain.exception.DomainException;
import com.treinamento.domain.model.Cliente;
import com.treinamento.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CrudClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
        Optional<Cliente> clienteResponse = clienteRepository.findByEmail(cliente.getEmail());
       if(clienteResponse.isPresent()){
           if(clienteResponse.equals(cliente)){
               return clienteRepository.save(cliente);
           }else{
               throw new DomainException("Email já está em uso");
           }
        }else{
           return clienteRepository.save(cliente);
       }

    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
