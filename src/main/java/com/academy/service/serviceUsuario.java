package com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.Dao.UsuarioDao;
import com.academy.Exceptions.CriptoExistException;
import com.academy.Exceptions.EmailExistsException;
import com.academy.Exceptions.ServiceExc;
import com.academy.model.Usuario;
import com.academy.util.Util;


@Service
public class serviceUsuario {
	
	@Autowired
	private UsuarioDao repositorio;
	
	public void SalvarUsuario(Usuario user)throws Exception {
		try {
			if(repositorio.findByEmail(user.getEmail())!=null) {
				throw new EmailExistsException("Ja existe um email cadastrado para : "+user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na Criptografia da senha");
		}
		repositorio.save(user);
	}
	public Usuario LoginUser(String user, String senha)throws ServiceExc {
		Usuario userLogin=repositorio.buscarLogin(user, senha);
		return userLogin;
	}
}
