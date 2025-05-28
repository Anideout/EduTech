//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.dto;

public class RolDto {
    private int id;
    private String nombre;
    private String email;
    private String rut;

    public RolDto(int id, String email, String nombre, String rut) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.rut = rut;
    }

    public RolDto() {
        this.id = 0;
        this.email = "";
        this.nombre = "";
        this.rut = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
