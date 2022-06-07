package com.academy.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.Dao.AlunoDao;
import com.academy.model.Aluno;

import java.util.List;

import javax.validation.Valid;

@Controller
public class AlunoControler {

	@Autowired
	private AlunoDao alunoDao;
	
	@GetMapping("/inserirAlunos")
	public ModelAndView inserirAlunos(Aluno aluno) {	
            ModelAndView mv=new ModelAndView();
            mv.setViewName("Aluno/formAluno");
            mv.addObject("aluno", new Aluno());
            return mv;
	}
	
	@PostMapping("/InsertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
		ModelAndView mv=new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Aluno/formAluno");
			mv.addObject("aluno");
		}else {
		mv.setViewName("redirect:/alunos-adicionados");
		alunoDao.save(aluno);
		}
		return mv;
	}
	
	@GetMapping("/alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/listAlunos");
		mv.addObject("alunoList",alunoDao.findAll());
		return mv;
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/alterar");
		Aluno aluno=alunoDao.getById(id);
		mv.addObject("aluno",aluno);
		return mv;
	}
	
	@PostMapping("alterar")
	public ModelAndView alterar(Aluno aluno) {
		ModelAndView mv=new ModelAndView();
		alunoDao.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		alunoDao.deleteById(id);
		return "redirect:/alunos-adicionados";
	}
	
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/filtroAlunos");
		mv.addObject("aluno",new Aluno());
		return mv;
	}
	
	@GetMapping("/alunos-activos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/alunos-activos");
		mv.addObject("alunoAcivos",alunoDao.findByEstadoAtivos());
		return mv;
	}
	
	@GetMapping("/alunos-inactivos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/alunos-inactivos");
		mv.addObject("alunoinacivos",alunoDao.findByEstadoInativo());
		return mv;
	}
	
	@GetMapping("/alunos-cancelados")
	public ModelAndView listaAlunosCancelados() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/alunos-cancelados");
		mv.addObject("alunocancelados",alunoDao.findByEstadoCancelados());
		return mv;
	}
	
	@GetMapping("/alunos-trancados")
	public ModelAndView listaAlunosTrancados() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Aluno/alunos-trancados");
		mv.addObject("alunotrancados",alunoDao.findByEstadoTrancados());
		return mv;
	}
	
	@PostMapping("/pesquisar-aluno")
	public ModelAndView pesquisarAlunos(@RequestParam(required = false)String nome) {
		ModelAndView mv=new ModelAndView();
		List<Aluno> listaAlunos;
		if(nome==null||nome.trim().isEmpty()) {
			listaAlunos=alunoDao.findAll();
		}else {
			listaAlunos=alunoDao.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("ListaAlunos",listaAlunos);
		mv.setViewName("Aluno/pesquisa-resultado");
		return mv;
	}
}
