package com.edutech.edutech.dto;

public class UsuarioModificarDto {
    private String email;
    private String contrasena;

    public UsuarioModificarDto(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    public UsuarioModificarDto() {
        this.email = "";
        this.contrasena = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
