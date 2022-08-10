package com.treinamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.domain.entity.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{

}
