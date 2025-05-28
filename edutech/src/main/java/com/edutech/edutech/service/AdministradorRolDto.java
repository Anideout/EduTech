package com.edutech.edutech.service;

public class AdministradorRolDto {

    private String rut;
    private int id;

    public AdministradorRolDto(String rut, int id) {
        this.rut = rut;
        this.id = id;
    }

    public AdministradorRolDto() {
        this.rut = "";
        this.id = 0;
    }

    

    /**
     * @return String return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
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
