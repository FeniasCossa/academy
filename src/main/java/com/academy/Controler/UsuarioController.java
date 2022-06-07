package com.academy.Controler;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.Dao.UsuarioDao;
import com.academy.Exceptions.ServiceExc;
import com.academy.model.Aluno;
import com.academy.model.Usuario;
import com.academy.service.serviceUsuario;
import com.academy.util.Util;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuariorepositoriy;
	
	@Autowired
	private serviceUsuario serviceUsuario;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno",new Aluno());
		return mv;
	}
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Login/login");
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("Login/cadastro");
		return mv;
	}
	
	@PostMapping("salvarUsuario")
	public ModelAndView cadastar(@Valid Usuario usuario,BindingResult br) throws Exception {
		ModelAndView mv=new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Login/login");
                        mv.addObject("usuario");
		}else{
                    serviceUsuario.SalvarUsuario(usuario);
                    mv.setViewName("redirect:/");  
                }  
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc{
		ModelAndView mv=new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("Login/login");
		}
		Usuario userLogin=serviceUsuario.LoginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
		if(userLogin==null) {
			mv.addObject("msg","Usuario nao encotrado. Tente Novamente");
		}else {
				session.setAttribute("usuarioLogado", userLogin);
				return index();
			}
		return mv;
	}
	
	@PostMapping("/logout")
	public ModelAndView logout(HttpSession sesseion) {
		sesseion.invalidate();
		return login();
	}
}
