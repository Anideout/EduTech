package com.edutech.edutech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
    @Id
    private String email;
    private String contrasena;

    @OneToOne(mappedBy = "usuario")
    @JsonBackReference
    private Persona persona;

    @ManyToMany
    @JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_rut"), inverseJoinColumns = @JoinColumn(name = "perfil_tag"))
    private List<Perfil> perfiles;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Asistencia> asistencia;

    @ManyToMany
    @JoinTable(name = "alumno_curso", joinColumns = @JoinColumn(name = "usuario_rut"), inverseJoinColumns = @JoinColumn(name = "curso_sigla"))
    private List<Curso> cursos;

    public Usuario() {
        this.email = "";
        this.contrasena = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Asistencia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<Asistencia> asistencia) {
        this.asistencia = asistencia;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

}
