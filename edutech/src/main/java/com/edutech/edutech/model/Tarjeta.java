package com.edutech.edutech.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nroTarjeta")
@Entity
public class Tarjeta {
    @Id
    public String nroTarjeta;
    public String titular;

    @ManyToOne
    @JoinColumn(name = "usuario_email")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "formaPago_")
    @JsonIgnore
    private FormaPago formaPago;

    public Tarjeta() {
        this.nroTarjeta = "";
        this.titular = "";
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

}
