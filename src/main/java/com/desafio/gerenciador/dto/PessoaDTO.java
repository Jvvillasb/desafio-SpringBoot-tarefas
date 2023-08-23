package com.desafio.gerenciador.dto;

import java.io.Serializable;

import com.desafio.gerenciador.entities.Pessoa;

public class PessoaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;

	private String nome;
	
	private long idDepartamento;
	
	private int horas;

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.idDepartamento = pessoa.getIdDepartamento().getId();
	}

	public PessoaDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Object map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
