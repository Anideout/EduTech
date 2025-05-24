package com.edutech.edutech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Evaluacion {
    @Id
    private int sigla;
    private String nombre;
    private String nota;

    public Evaluacion(){ 
        this.sigla = 0;
        this.nombre = "";
        this.nota = "";
    }

    

    /**
     * @return int return the sigla
     */
    public int getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(int sigla) {
        this.sigla = sigla;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

}
