package com.treinamento.api.controller;

import com.treinamento.api.input.ClienteInput;
import com.treinamento.api.mapper.ClienteMapper;
import com.treinamento.api.output.ClienteIdOutput;
import com.treinamento.api.output.ClienteOutput;
import com.treinamento.domain.entity.Cliente;
import com.treinamento.domain.repository.ClienteRepository;
import com.treinamento.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	private ClienteMapper clienteMapper;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteOutput> buscar(@PathVariable Long clienteId){
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente)))
						.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteOutput adicionar(@Valid @RequestBody ClienteInput clienteInput){
		Cliente newCliente = clienteMapper.toConvert(clienteInput);
		return clienteMapper.toModel(clienteService.salvar(newCliente));
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> modificar(@Valid
											 @PathVariable Long clienteId,
											 @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			cliente.setId(clienteId);
			cliente = clienteService.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}else{
			clienteService.excluir(clienteId);
			return ResponseEntity.noContent().build();
		}
	}
	
}
