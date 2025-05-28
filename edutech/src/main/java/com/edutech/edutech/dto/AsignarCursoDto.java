//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.dto;

public class AsignarCursoDto {
    public String sigla;
    public int id;
    public String rut;
    public String email;

    public AsignarCursoDto(String sigla, int id, String rut, String email) {
        this.sigla = sigla;
        this.id = id;
        this.rut = rut;
        this.email = email;
    }

    public AsignarCursoDto() {
        this.sigla = "";
        this.id = 0;
        this.rut = "";
        this.email = "";
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
