package com.academy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    @Size(min = 3, max = 35, message = "o nome deve conter o minimo {min} caracteres e no maximo {max}")
    @NotNull(message = "O nome nao pode ser vazio.")
    private String nome;

    @Column(name = "curso")
    @NotNull(message = "O campo curso nao pode ser nulo")
    private String curso;

    @Column(name = "matricula")
    @NotNull(message = "Clique no botão Gerar matricula")
    @Size(min = 4, max = 35, message = "clique no botao Gerar pr gerar matricula")
    private String matricula;

    @NotNull(message = "O campo Estado nao pode ser nulo")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "O campo turno nao pode ser nulo")
    @Column(name = "turno")
    private String turno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ""
                + ", nome=" + nome + ", "
                + "curso=" + curso + ", "
                + "matricula=" + matricula + ", "
                + "estado=" + estado + ", "
                + "turno=" + turno + '}';
    }

}
