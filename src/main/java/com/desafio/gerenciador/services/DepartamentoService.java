package com.desafio.gerenciador.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.gerenciador.dto.DepartamentoDTO;
import com.desafio.gerenciador.dto.DepartamentoSUMDTO;
import com.desafio.gerenciador.entities.Departamento;
import com.desafio.gerenciador.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public List<DepartamentoSUMDTO> getDeptoAndTarefas() {
		List<DepartamentoSUMDTO> dto = departamentoRepository.findAll().stream().map(x -> new DepartamentoSUMDTO(x)).collect(Collectors.toList());
		return dto;
	}
	
	public List<Departamento> getDpto(){
		return departamentoRepository.findAll();
	}
	
	public DepartamentoDTO createDepto(@RequestBody DepartamentoDTO dto) {
		if(departamentoRepository.findAllByTitulo(dto.getTitulo()).isEmpty()) {
			Departamento dpto = new Departamento();
			dpto.setTitulo(dto.getTitulo());
			departamentoRepository.save(dpto);
			return new DepartamentoDTO(dpto);
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
	public DepartamentoDTO udpateDepto(@RequestBody DepartamentoDTO dto, Long id) {
		if(departamentoRepository.findById(id).isPresent()) {
			Departamento dpto = departamentoRepository.getReferenceById(id);
			dpto.setTitulo(dto.getTitulo());
			departamentoRepository.save(dpto);
			return new DepartamentoDTO(dpto);
		} else {
			throw new ErrorResponseException(HttpStatusCode.valueOf(400));
		}
	}
	
	public void delete(Long id){
		try {
			departamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ExceptionInInitializerError("Id not found " + id);
		}
	}
}
