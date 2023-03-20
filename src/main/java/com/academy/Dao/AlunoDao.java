package com.academy.Dao;


import com.academy.model.Aluno;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

	@Query("Select j from Aluno j where j.estado = 'ATIVO' ")
	public List<Aluno> findByEstadoAtivos();
	
	@Query("Select f from Aluno f where f.estado = 'INATIVO' ")
	public List<Aluno> findByEstadoInativo();
	
	@Query("Select c from Aluno c where c.estado = 'CANCELADO' ")
	public List<Aluno> findByEstadoCancelados();
	
	@Query("Select t from Aluno t where t.estado = 'TRANCADO' ")
	public List<Aluno> findByEstadoTrancados();
	
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);

	public List<Aluno> findAllById(Integer id);
}
