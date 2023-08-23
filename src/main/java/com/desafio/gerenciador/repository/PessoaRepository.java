package com.desafio.gerenciador.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.gerenciador.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	public List<Pessoa> findAllByNome(String nome);
	
	public List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);
	
	@Query(nativeQuery = true, value = "SELECT P.id, P.id_departamento, P.nome, T.prazo FROM tb_pessoa AS P INNER JOIN tb_tarefa AS T ON T.pessoa = P.id WHERE P.nome = :nome AND T.prazo BETWEEN :dataMin AND :dataMax LIMIT 1")
	public List<Pessoa> findAllPessoasAndTarefas(String nome, LocalDate dataMin, LocalDate dataMax);

}
