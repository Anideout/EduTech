package com.edutech.edutech.dto;

import com.edutech.edutech.model.Persona;

public class UsuarioPersonaDto {
    private String email;
    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    public UsuarioPersonaDto() {
        this.email = "";
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
    }

    public UsuarioPersonaDto(String email,Persona persona) {
        this.email = email;
        this.rut = rut;
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getdireccion() {
        return direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

}
