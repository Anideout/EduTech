package com.edutech.edutech.dto;

public class EspecialidadDTO {

    private String nombre;

    public EspecialidadDTO(String nombre) {
        this.nombre = nombre;
    }

    public EspecialidadDTO() {
        this.nombre = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
