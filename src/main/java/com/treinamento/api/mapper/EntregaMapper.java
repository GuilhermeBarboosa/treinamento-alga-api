package com.treinamento.api.mapper;

import com.treinamento.api.input.EntregaInput;
import com.treinamento.api.output.EntregaOutput;
import com.treinamento.domain.entity.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaOutput toModel(Entrega entrega){
        return  modelMapper.map(entrega, EntregaOutput.class);
    }

    public List<EntregaOutput> toListOutput(List<Entrega> entregaList){
        return  entregaList.stream()
                            .map(this::toModel)
                            .collect(Collectors.toList());
    }

    public Entrega toConvert(EntregaInput entregaInput){
        return  modelMapper.map(entregaInput, Entrega.class);
    }
}
