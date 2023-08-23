package com.desafio.gerenciador.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.gerenciador.dto.TarefaDTO;
import com.desafio.gerenciador.dto.TarefaIDDTO;
import com.desafio.gerenciador.entities.Tarefa;
import com.desafio.gerenciador.services.TarefaService;

@RestController
@RequestMapping
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/get/tarefas/all")
	public ResponseEntity<List<Tarefa>> findAll() {
		return ResponseEntity.ok(tarefaService.getTarefas());
	}
	
	@GetMapping("/get/tarefas/pendentes")
	public ResponseEntity<List<TarefaDTO>> findPendentes() {
		return ResponseEntity.ok(tarefaService.getTarefasPend());
	}
	
	@PostMapping("/post/tarefas")
	public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO dto) {
		TarefaDTO newDto = tarefaService.createTarefa(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping("/put/tarefas/{id}")
	public ResponseEntity<TarefaDTO> update(@RequestBody TarefaDTO dto, @PathVariable Long id) {
		TarefaDTO newDto = tarefaService.updateTarefa(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@PutMapping("/put/tarefas/alocar/{id}")
	public ResponseEntity<TarefaIDDTO> updatePessoaTarefa(@RequestBody TarefaIDDTO dto, @PathVariable Long id) {
		TarefaIDDTO newDto = tarefaService.cadastrarPessoaTarefa(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@PutMapping("/put/tarefas/finalizar/{id}")
	public Long finalizarTarefa(@PathVariable Long id) {
		Long newId = (Long) tarefaService.finalizarTarefa(id);
		return newId;
	}
	
	@DeleteMapping("/delete/tarefa/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		tarefaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
