package com.academy.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	@Query("select i from Usuario i where i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select f from Usuario f where f.user = :user and f.senha = :senha")
	public Usuario buscarLogin(String user,String senha);
}
