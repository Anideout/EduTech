package com.edutech.edutech.dto;

import com.edutech.edutech.model.Persona;

public class UsuarioDto {

    private String email;
    private String nombre;
    private String apellido;
    private String direccion;

    public UsuarioDto(String email, Persona persona) {
        this.email = email;
        if (persona != null) {
            this.nombre = persona.getNombre();
            this.apellido = persona.getApellido();
            this.direccion = persona.getDireccion();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
