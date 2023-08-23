package com.desafio.gerenciador.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.desafio.gerenciador.dto.TarefaDTO;
import com.desafio.gerenciador.dto.TarefaIDDTO;
import com.desafio.gerenciador.entities.Departamento;
import com.desafio.gerenciador.entities.Pessoa;
import com.desafio.gerenciador.entities.Tarefa;
import com.desafio.gerenciador.repository.DepartamentoRepository;
import com.desafio.gerenciador.repository.PessoaRepository;
import com.desafio.gerenciador.repository.TarefaRepository;

import jakarta.transaction.Transactional;


@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Tarefa> getTarefas() {
		return tarefaRepository.findAll();
	}
	
	public List<TarefaDTO> getTarefasPend() {
		List<TarefaDTO> dto = tarefaRepository.findPend().stream().map(x -> new TarefaDTO(x)).collect(Collectors.toList());
		return dto;
	}
	
	public Long finalizarTarefa(long dto) {
		if (tarefaRepository.findById(dto).isPresent()) {
			Tarefa tarefa = tarefaRepository.getReferenceById(dto);
			tarefa.setFinalizado(true);
			tarefaRepository.save(tarefa);
			return dto;
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
	public TarefaIDDTO cadastrarPessoaTarefa(@RequestBody TarefaIDDTO dto, long id) {
		Tarefa tarefa = tarefaRepository.getReferenceById(dto.getIdTarefa());
		Pessoa pessoa = pessoaRepository.getReferenceById(id);
		if (tarefaRepository.findById(id).isPresent() && pessoaRepository.findById(id).isPresent() && tarefa.getIdDepartamento() == pessoa.getIdDepartamento()) {
			tarefa.setPessoa(pessoa);
			tarefaRepository.save(tarefa);
			return dto;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provavelmente a pessoa não pertence a esse Departamento");
		}
	}
	
	public TarefaDTO updateTarefa(@RequestBody TarefaDTO dto, long id) {
		if (tarefaRepository.findById(id).isPresent() && departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Tarefa tarefa = tarefaRepository.getReferenceById(id);
			Departamento departamento = new Departamento();
			tarefa.setTitulo(dto.getTitulo());
			tarefa.setDescricao(dto.getDescricao());
			tarefa.setPrazo(dto.getPrazo());
			tarefa.setDuracao(dto.getDuracao());
			departamento.setId(dto.getIdDepartamento());
			tarefa.setIdDepartamento(departamento);
			tarefaRepository.save(tarefa);
			return dto;
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
		@Transactional
		public TarefaDTO createTarefa(@RequestBody TarefaDTO dto) {
			if (tarefaRepository.findAllByTitulo(dto.getTitulo()).isEmpty() && departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
				Departamento departamento = new Departamento();
				departamento.setId(dto.getIdDepartamento());
				Tarefa tarefa = new Tarefa();
				tarefa.setTitulo(dto.getTitulo());
				tarefa.setDescricao(dto.getDescricao());
				tarefa.setPrazo(dto.getPrazo());
				tarefa.setDuracao(dto.getDuracao());
				tarefa.setIdDepartamento(departamento);
	            tarefaRepository.save(tarefa);
	            tarefaRepository.updateNull(dto.getTitulo());
				return dto;
			}  else {
				throw new ErrorResponseException(HttpStatusCode.valueOf(400));
			}
		}
		
		public void delete(Long id){
			try {
				tarefaRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ExceptionInInitializerError("Id not found " + id);
			}
		}
}
