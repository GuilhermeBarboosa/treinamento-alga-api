package com.treinamento.codiub.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.codiub.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Guilherme Barbosa");
		cliente1.setEmail("guilherme@gmail.com");
		cliente1.setTelefone("34 99883-3432");

		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria Clara");
		cliente2.setEmail("maria@gmail.com.br");
		cliente2.setTelefone("34 94343-34232");
		
		return Arrays.asList(cliente1, cliente2);
	}
	
}
