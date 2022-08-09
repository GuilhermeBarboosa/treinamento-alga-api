package com.treinamento.api.controller;

import java.util.List;
import java.util.Optional;

import com.treinamento.domain.entity.Cliente;
import com.treinamento.domain.repository.ClienteRepository;
import com.treinamento.domain.service.CrudClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CrudClienteService crudClienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if(cliente.isPresent()){
			return ResponseEntity.ok(cliente.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente){
		return crudClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> modificar(@Valid
											 @PathVariable Long clienteId,
											 @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			cliente.setId(clienteId);
			cliente = crudClienteService.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			crudClienteService.excluir(clienteId);
			return ResponseEntity.noContent().build();
		}
	}
	
}
