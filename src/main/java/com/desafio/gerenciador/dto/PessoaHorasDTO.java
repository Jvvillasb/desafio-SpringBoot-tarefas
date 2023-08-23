package com.desafio.gerenciador.dto;

import java.io.Serializable;

import com.desafio.gerenciador.entities.Pessoa;
import com.desafio.gerenciador.entities.Tarefa;

public class PessoaHorasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	private String departamento;

	private long horasTarefas;

	public PessoaHorasDTO(Pessoa pessoa) {
		super();
		this.nome = pessoa.getNome();
		this.departamento = pessoa.getIdDepartamento().getTitulo();
		long totalDuracao = pessoa.getTarefa().stream().mapToLong(Tarefa::getDuracao).sum();
	    this.horasTarefas = totalDuracao;
	}

	public PessoaHorasDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public long getHorasTarefas() {
		return horasTarefas;
	}

	public void setHorasTarefas(long horasTarefas) {
		this.horasTarefas = horasTarefas;
	}

}
