package com.edutech.edutech.dto;

public class UsuarioEmailDto {
    public String email;

    public UsuarioEmailDto(String email) {
        this.email = email;
    }

    public UsuarioEmailDto() {
        this.email = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
