package com.edutech.edutech.dto;

public class ActualizarCursoDto {
    private String sigla;
    private String nombre;
    private String descripcion;
    private String estado;
    private int valor;

    public ActualizarCursoDto(String sigla, String nombre, String descripcion, String estado, int valor) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.valor = valor;
    }

    public ActualizarCursoDto() {
        this.sigla = "";
        this.nombre = "";
        this.descripcion = "";
        this.estado = "";
        this.valor = 0;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
