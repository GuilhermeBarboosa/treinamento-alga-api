package com.treinamento.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.treinamento.api.input.EntregaInput;
import com.treinamento.api.input.OcorrenciaInput;
import com.treinamento.api.output.EntregaOutput;
import com.treinamento.api.output.OcorrenciaOutput;
import com.treinamento.domain.entity.Entrega;
import com.treinamento.domain.entity.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {
	  private ModelMapper modelMapper;

	    public OcorrenciaOutput toModel(Ocorrencia ocorrencia){
	        return  modelMapper.map(ocorrencia, OcorrenciaOutput.class);
	    }

	    public List<OcorrenciaOutput> toListOutput(List<Ocorrencia> ocorrenciaList){
	        return  ocorrenciaList.stream()
	                            .map(this::toModel)
	                            .collect(Collectors.toList());
	    }

	    public Ocorrencia toConvert(OcorrenciaInput ocorrenciaInput){
	        return  modelMapper.map(ocorrenciaInput, Ocorrencia.class);
	    }
}
