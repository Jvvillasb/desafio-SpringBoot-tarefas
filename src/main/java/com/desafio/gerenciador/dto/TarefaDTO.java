package com.desafio.gerenciador.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.desafio.gerenciador.entities.Tarefa;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TarefaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate prazo;
	
	private int duracao;
		
	private long idDepartamento;public TarefaDTO(Tarefa tarefa) {
		super();
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.prazo = tarefa.getPrazo();
		this.duracao = tarefa.getDuracao();
		this.idDepartamento = tarefa.getIdDepartamento().getId();
	}

	public TarefaDTO() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
