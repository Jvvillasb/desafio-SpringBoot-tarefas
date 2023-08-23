package com.desafio.gerenciador.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.gerenciador.dto.PessoaDTO;
import com.desafio.gerenciador.dto.PessoaHorasDTO;
import com.desafio.gerenciador.dto.PessoaHorasMediaDTO;
import com.desafio.gerenciador.entities.Departamento;
import com.desafio.gerenciador.entities.Pessoa;
import com.desafio.gerenciador.repository.DepartamentoRepository;
import com.desafio.gerenciador.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRespository;
	
	
	public List<PessoaHorasDTO> findPessoas() {
		List<PessoaHorasDTO> dto = pessoaRepository.findAll().stream().map(x -> new PessoaHorasDTO(x)).collect(Collectors.toList());
		return dto;
	}
	
	public List<PessoaHorasMediaDTO> findPessoaMediaHoras(String dto, LocalDate dataMin, LocalDate dataMax) {
		List<PessoaHorasMediaDTO> PessoaMediaRetornoDTO = pessoaRepository
				.findAllPessoasAndTarefas(dto, dataMin, dataMax).stream()
				.map(x -> new PessoaHorasMediaDTO(x)).collect(Collectors.toList());
		return PessoaMediaRetornoDTO;
	}
	
	public PessoaDTO createPessoa(@RequestBody PessoaDTO dto){
		if (departamentoRespository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento depto = new Departamento();
			depto.setId(dto.getIdDepartamento());
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(dto.getNome());
			pessoa.setIdDepartamento(depto);
			pessoaRepository.save(pessoa);
			return new PessoaDTO(pessoa);
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
	public PessoaDTO udpatePessoa(@RequestBody PessoaDTO dto, Long id){
		if (pessoaRepository.findById(id).isPresent() && departamentoRespository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento depto = new Departamento();
			depto.setId(dto.getIdDepartamento());
			Pessoa pessoa = pessoaRepository.getReferenceById(id);
			pessoa.setNome(dto.getNome());
			pessoa.setIdDepartamento(depto);
			pessoaRepository.save(pessoa);
			return new PessoaDTO(pessoa);
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
	public void delete(Long id){
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ExceptionInInitializerError("Id not found " + id);
		}
	}

}