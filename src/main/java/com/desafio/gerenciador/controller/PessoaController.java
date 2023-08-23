package com.desafio.gerenciador.controller;

import java.net.URI;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.gerenciador.dto.PessoaDTO;
import com.desafio.gerenciador.dto.PessoaHorasDTO;
import com.desafio.gerenciador.dto.PessoaHorasMediaDTO;
import com.desafio.gerenciador.services.PessoaService;

@RestController
@RequestMapping
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("get/pessoas")
	public ResponseEntity<List<PessoaHorasDTO>> findAll(){
		return ResponseEntity.ok(pessoaService.findPessoas());
	}
	
	@GetMapping("get/pessoas/gastos")
	public ResponseEntity<List<PessoaHorasMediaDTO>> findByNameAndAvgHoras(@RequestParam(value="nome", defaultValue = "") String dto,
																		   @RequestParam(value="dataMin", defaultValue = "1949-01-01") LocalDate dataMin,
																		   @RequestParam(value="dataMax", defaultValue = "2023-08-22") LocalDate dataMax) {
		return ResponseEntity.ok(pessoaService.findPessoaMediaHoras(dto, dataMin, dataMax));
	}
	
	@PostMapping("/post/pessoas")
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO dto) {
		PessoaDTO newDto = pessoaService.createPessoa(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping("/put/pessoas/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO dto) {
		PessoaDTO newDto = pessoaService.udpatePessoa(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping("/delete/pessoas/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
