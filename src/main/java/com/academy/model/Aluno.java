package com.academy.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.hibernate.annotations.Type;

import com.academy.Enums.Curso;
import com.academy.Enums.Estado;
import com.academy.Enums.Turno;

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
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo curso nao pode ser nulo")
    private Curso curso;

    @Column(name = "matricula")
    @NotNull(message = "Clique no botão Gerar matricula")
    @Size(min = 4, max = 35, message = "clique no botao Gerar pr gerar matricula")
    private String matricula;

    @NotNull(message = "O campo Estado nao pode ser nulo")
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotNull(message = "O campo turno nao pode ser nulo")
    @Column(name = "turno")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
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
