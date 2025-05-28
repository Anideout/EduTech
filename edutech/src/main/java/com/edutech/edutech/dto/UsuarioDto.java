//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.dto;

public class UsuarioDto {

    private String email;
    private String nombre;
    private String apellido;
    private String direccion;
    private String rol;

    public UsuarioDto(String email, String nombre, String apellido, String direccion, String rol) {
        this.email = email;
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.rol = "";

    }

    public UsuarioDto() {
        this.email = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.rol = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
