package com.edutech.edutech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
    @Id
    private String email;
    private String contrasena;

    @OneToOne
    @JoinColumn(name = "rut_persona", referencedColumnName = "rut")
    // jsonManagedReference va de la mano con jsonbackReference,
    // para evitar bucles, es necesario cuando se tiene más de una relacion con el
    // mismo nombre
    @JsonManagedReference("usuario-persona")
    private Persona persona;

    // @ManyToMany
    // @JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name =
    // "usuario_rut"), inverseJoinColumns = @JoinColumn(name = "perfil_tag"))
    // private List<Perfil> perfiles;

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
    // @ManyToMany

    // @JoinTable(name = "alumno_curso", joinColumns = @JoinColumn(name =
    // "usuario_rut"), inverseJoinColumns = @JoinColumn(name = "curso_sigla"))
    // private List<Curso> cursos;

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

}
