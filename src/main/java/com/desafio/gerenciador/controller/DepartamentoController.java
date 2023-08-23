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

import com.desafio.gerenciador.dto.DepartamentoDTO;
import com.desafio.gerenciador.dto.DepartamentoSUMDTO;
import com.desafio.gerenciador.services.DepartamentoService;

@RestController
@RequestMapping
public class DepartamentoController {

	@Autowired	
	private DepartamentoService departamentoService;
	
	@GetMapping("/get/departamentos")
	public ResponseEntity<List<DepartamentoSUMDTO>> findAll() {
		return ResponseEntity.ok(departamentoService.getDeptoAndTarefas());
	}
	
	@PostMapping("/post/departamento")
	public ResponseEntity<DepartamentoDTO> create(@RequestBody DepartamentoDTO dto) {
		DepartamentoDTO newDto = departamentoService.createDepto(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping("/put/departamento/{id}")
	public ResponseEntity<DepartamentoDTO> update(@RequestBody DepartamentoDTO dto, @PathVariable Long id) {
		DepartamentoDTO newDto = departamentoService.udpateDepto(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping("/delete/departamento/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		departamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
