package com.treinamento.api.mapper;

import com.treinamento.api.input.ClienteInput;
import com.treinamento.api.output.ClienteOutput;
import com.treinamento.api.output.EntregaOutput;
import com.treinamento.domain.entity.Cliente;
import com.treinamento.domain.entity.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {

    private ModelMapper modelMapper;

    public ClienteOutput toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteOutput.class);
    }

    public Cliente toConvert(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public List<ClienteOutput> toListOutput(List<Cliente> ClienteList){
        return  ClienteList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
