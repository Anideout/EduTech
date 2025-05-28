//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.dto;

public class RolDto {
    private int id;
    private String email;
    private String rut;

    public RolDto(int id, String email, String rut) {
        this.id = id;
        this.email = email;
        this.rut = rut;
    }

    public RolDto() {
        this.id = 0;
        this.email = "";
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

}
