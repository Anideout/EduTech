package com.edutech.edutech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Curso {
    @Id
    private int sigla;
    private String nombre;
    private String descripcion;
    private String estado;
    private int valor;

    /*
     * @ManyToMany(mappedBy = "cursos")
     * private List<Usuario> usuarios;
     */

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    private Evaluacion evaluacion;

    public Curso() {
        this.sigla = 0;
        this.nombre = "";
        this.descripcion = "";
        this.estado = "";
        this.valor = 0;
    }

    public int getSigla() {
        return sigla;
    }

    public void setSigla(int sigla) {
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

    /*
     * public List<Usuario> getUsuarios() {
     * return usuarios;
     * }
     * 
     * public void setUsuarios(List<Usuario> usuarios) {
     * this.usuarios = usuarios;
     * }
     */

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}
