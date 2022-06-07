package com.academy.Enums;

public enum Curso {

	
	ADMISTRACAO("Admistracao"),
	INFORMATICA("Informatica"),
	CONTABILIDADE("Contabilidade"),
	PROGRAMACAO("Programacao"),
	ENFERMAGEM("Enfermagem");
  
	
	private String curso;
	
	private Curso(String curso) {
		this.setCurso(curso);
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}
