package com.treinamento.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.treinamento.api.exceptionhandler.ApiExceptionHandler;
import com.treinamento.domain.entity.Entrega;
import com.treinamento.domain.entity.Ocorrencia;
import com.treinamento.domain.exception.DomainException;
import com.treinamento.domain.repository.EntregaRepository;
import com.treinamento.domain.repository.OcorrenciaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OcorrenciaService {
	
	private EntregaRepository entregaRepository;
	private OcorrenciaRepository ocorrenciaRepository;
	
	private EntregaService entregaService;

	@Transactional
	public Ocorrencia registrar(Ocorrencia novaOcorrencia) {
		Entrega entrega = entregaService.buscarEntrega(novaOcorrencia.getEntrega().getId());
		
		novaOcorrencia.setDataRegistro(OffsetDateTime.now());
		return ocorrenciaRepository.save(novaOcorrencia);
	}
}
