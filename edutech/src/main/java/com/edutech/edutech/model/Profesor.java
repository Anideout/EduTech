package com.edutech.edutech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profesor {
    @Id
    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String contrasena;
    private String especialidad;

    public Profesor() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.contrasena = "";
        this.especialidad = "";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
