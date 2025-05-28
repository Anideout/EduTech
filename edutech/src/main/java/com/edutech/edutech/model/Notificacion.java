//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notificacion {
    @Id
    // asignar un id del 1 en adelante
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mensaje;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_email")
    @JsonIgnore
    private Usuario usuario;

    public Notificacion() {
        this.id = 0;
        this.mensaje = "";
        this.fecha = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
