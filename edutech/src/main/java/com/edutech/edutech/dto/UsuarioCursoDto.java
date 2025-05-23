package com.edutech.edutech.dto;

public class UsuarioCursoDto {

    private String rut;
    private int sigla;

    public UsuarioCursoDto(String rut, int sigla) {
        this.rut = rut;
        this.sigla = sigla;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getSigla() {
        return sigla;
    }

    public void setSigla(int sigla) {
        this.sigla = sigla;
    }

}
