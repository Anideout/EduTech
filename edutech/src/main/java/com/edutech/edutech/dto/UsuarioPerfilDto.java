package com.edutech.edutech.dto;

public class UsuarioPerfilDto {

    private String rut;
    private String tag;

    public UsuarioPerfilDto(String rut, String tag) {
        this.rut = rut;
        this.tag = tag;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
