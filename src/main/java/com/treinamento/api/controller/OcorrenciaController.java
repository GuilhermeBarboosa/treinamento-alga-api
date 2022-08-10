package com.treinamento.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.api.input.OcorrenciaInput;
import com.treinamento.api.mapper.OcorrenciaMapper;
import com.treinamento.api.output.EntregaOutput;
import com.treinamento.api.output.OcorrenciaOutput;
import com.treinamento.domain.entity.Ocorrencia;
import com.treinamento.domain.repository.OcorrenciaRepository;
import com.treinamento.domain.service.OcorrenciaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	private OcorrenciaRepository ocorrenciaRepository;
	private OcorrenciaService ocorrenciaService;
	private OcorrenciaMapper ocorrenciaMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaOutput registrar(@RequestBody @Valid OcorrenciaInput ocorrenciaInput) {
		Ocorrencia novaOcorrencia = ocorrenciaMapper.toConvert(ocorrenciaInput);
		return ocorrenciaMapper.toModel(ocorrenciaService.registrar(novaOcorrencia));
	}

	@GetMapping
	public List<OcorrenciaOutput> listar() {
		return ocorrenciaMapper.toListOutput(ocorrenciaRepository.findAll());
	}

}
