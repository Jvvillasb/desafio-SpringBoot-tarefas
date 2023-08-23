package com.desafio.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.gerenciador.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	public List<Departamento> findAllByTitulo(String titulo);

}
