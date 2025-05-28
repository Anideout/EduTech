//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.dto;

public class AsignarUsuarioDto {
    private String email;
    private int id;
    private String rut;

    public AsignarUsuarioDto(String email, int id, String rut) {
        this.email = email;
        this.id = id;
        this.rut = rut;
    }

    public AsignarUsuarioDto() {
        this.email = "";
        this.id = 0;
        this.rut = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
