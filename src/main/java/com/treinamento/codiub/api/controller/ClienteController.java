package com.treinamento.codiub.api.controller;

import java.util.List;

import com.treinamento.codiub.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;
	@GetMapping("/clientes")
	public List listar() {
		return manager.createNativeQuery("select * from clientes", Cliente.class)
				.getResultList();
	}
	
}
