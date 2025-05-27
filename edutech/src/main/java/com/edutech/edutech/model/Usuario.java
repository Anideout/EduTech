package com.edutech.edutech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "email")
@Entity
public class Usuario {
    @Id
    private String email;
    private String contrasena;

    @OneToOne
    @JoinColumn(name = "rut_persona", referencedColumnName = "rut")

    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Tarjeta> tarjeta;
    @OneToOne
    @JoinColumn(name = "id_resenia", referencedColumnName = "id")
    private Resenia resenia;
    // mapped hace referencia al atributo usuario en la clase asistencia,
    // cascade hace que al eliminar un usuario,
    // se elimine la asistencia. y orphan es para eliminar las asistencias si se
    // borran de la lista
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripcion;

    @ManyToMany
    @JoinTable(name = "usuario_curso", joinColumns = @JoinColumn(name = "usuario_email"), inverseJoinColumns = @JoinColumn(name = "curso_sigla"))
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

    /**
     * @return Rol return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Tarjeta> getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(List<Tarjeta> tarjeta) {
        this.tarjeta = tarjeta;
    }

}
