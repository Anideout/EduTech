package com.edutech.edutech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
    @Id
    private String email;
    private String contrasena;
    private String rol;

    @OneToOne(mappedBy = "usuario")
    private Profesor profesor;

    @OneToOne(mappedBy = "usuario")
    private Persona persona;

    public Usuario() {
        this.email = "";
        this.contrasena = "";
        this.rol = "";
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
