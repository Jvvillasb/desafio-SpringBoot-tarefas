package com.desafio.gerenciador.dto;

import java.io.Serializable;

public class PessoaMediaHorasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	public PessoaMediaHorasDTO(String nome) {
		super();
		this.nome = nome;

	}

	public PessoaMediaHorasDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
