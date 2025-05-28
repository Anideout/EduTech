package com.edutech.edutech.dto;

public class EspecialidadDTO {
    private int id;
    private String nombre;

    public EspecialidadDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public EspecialidadDTO() {
        this.id = 0;
        this.nombre = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
