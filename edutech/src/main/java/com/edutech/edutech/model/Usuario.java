package com.edutech.edutech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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

    @OneToOne
    @JoinColumn(name = "rut_persona", referencedColumnName = "rut")
    // jsonManagedReference va en la entidad padre de la relación. de la mano con
    // jsonbackReference,
    // para evitar bucles, es necesario cuando se tiene más de una relacion con el
    // mismo nombre
    @JsonManagedReference("usuario-persona")
    private Persona persona;

    @OneToOne
    @JoinColumn(name = "id_resenia", referencedColumnName = "id")
    @JsonManagedReference("usuario-resenia")
    private Resenia resenia;
    // mapped hace referencia al atributo usuario en la clase asistencia,
    // cascade hace que al eliminar un usuario,
    // se elimine la asistencia. y orphan es para eliminar las asistencias si se
    // borran de la lista
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-asistencia")
    private List<Asistencia> asistencia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-notificacion")
    private List<Notificacion> notificacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-inscripcion")
    private List<Inscripcion> inscripcion;

    @ManyToMany
    @JoinTable(name = "usuario_curso", joinColumns = @JoinColumn(name = "usuario_email"), inverseJoinColumns = @JoinColumn(name = "curso_sigla"))
    @JsonIgnore
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

    public List<Notificacion> getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(List<Notificacion> notificacion) {
        this.notificacion = notificacion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Resenia getResenia() {
        return resenia;
    }

    public void setResenia(Resenia resenia) {
        this.resenia = resenia;
    }

    public List<Inscripcion> getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(List<Inscripcion> inscripcion) {
        this.inscripcion = inscripcion;
    }

}
