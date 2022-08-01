package com.treinamento.codiub.api.controller;

import java.util.List;
import java.util.Optional;

import com.treinamento.codiub.domain.model.Cliente;
import com.treinamento.codiub.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public List listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if(cliente.isPresent()){
			return ResponseEntity.ok(cliente.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}

	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> modificar(@Valid
											 @PathVariable Long clienteId,
											 @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			cliente.setId(clienteId);
			cliente = clienteRepository.save(cliente);
			return ResponseEntity.ok(cliente);
		}
	}

	@DeleteMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.noContent().build();
		}
	}
	
}
