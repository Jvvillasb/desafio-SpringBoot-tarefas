package com.desafio.gerenciador.dto;

import java.io.Serializable;

import com.desafio.gerenciador.entities.Departamento;

public class DepartamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String titulo;
	
	public DepartamentoDTO(Departamento departamento) {
		super();
		this.id = departamento.getId();
		this.titulo = departamento.getTitulo();
	}

	public DepartamentoDTO() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
