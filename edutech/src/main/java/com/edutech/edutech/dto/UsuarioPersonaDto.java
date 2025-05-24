package com.edutech.edutech.dto;

public class UsuarioPersonaDto {
    private String email;
    private String rut;

    public UsuarioPersonaDto() {
        this.email = "";
        this.rut = "";
    }

    public UsuarioPersonaDto(String email, String rut) {
        this.email = email;
        this.rut = rut;
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

}
