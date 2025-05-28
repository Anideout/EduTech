//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.dto;

public class SedeDto {
    private int id;
    private String rut;

    public SedeDto(int id, String rut) {
        this.id = id;
        this.rut = rut;
    }

    public SedeDto() {
        this.id = 0;
        this.rut = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

}
