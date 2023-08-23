package com.desafio.gerenciador.dto;

import java.io.Serializable;

import com.desafio.gerenciador.entities.Tarefa;

public class TarefaIDDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long idTarefa;
	
	public TarefaIDDTO(Tarefa tarefa) {
		super();
		this.idTarefa = tarefa.getId();
	}

	public TarefaIDDTO() {
		super();
	}

	public long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}
	
}
