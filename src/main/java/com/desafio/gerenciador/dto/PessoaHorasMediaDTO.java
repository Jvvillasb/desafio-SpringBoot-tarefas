package com.desafio.gerenciador.dto;

import java.io.Serializable;
import java.util.stream.Collectors;

import com.desafio.gerenciador.entities.Pessoa;
import com.desafio.gerenciador.entities.Tarefa;

public class PessoaHorasMediaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	private Double mediaHorasTarefas;

	public PessoaHorasMediaDTO(Pessoa pessoa) {
		super();
		this.nome = pessoa.getNome();
		double mediaDuracao = pessoa.getTarefa().stream().collect(Collectors.averagingInt(Tarefa::getDuracao));
		this.mediaHorasTarefas = mediaDuracao;
	}

	public PessoaHorasMediaDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMediaHorasTarefas() {
		return mediaHorasTarefas;
	}

	public void setMediaHorasTarefas(Double mediaHorasTarefas) {
		this.mediaHorasTarefas = mediaHorasTarefas;
	}

}
