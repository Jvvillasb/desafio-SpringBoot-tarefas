package com.desafio.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.gerenciador.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	public List<Tarefa> findAllByTitulo(String titulo);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_tarefa t WHERE t.pessoa = 1 ORDER BY t.prazo LIMIT 3")
	public List<Tarefa> findPend();
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE tb_tarefa SET pessoa = 1 WHERE titulo = :titulo")
	public void updateNull(String titulo);
}

