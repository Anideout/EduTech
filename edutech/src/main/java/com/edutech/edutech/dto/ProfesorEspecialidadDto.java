package com.edutech.edutech.dto;

public class ProfesorEspecialidadDto {
    private String rut;
    private int id;

    public ProfesorEspecialidadDto() {
        this.rut = "";
        this.id = 0;
    }

    public ProfesorEspecialidadDto(String rut, int id) {
        this.rut = rut;
        this.id = id;
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

}
