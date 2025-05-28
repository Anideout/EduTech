package com.edutech.edutech.dto;

public class AdministradorSedeDto {
    private int id;
    private String rut;

    public AdministradorSedeDto(int id, String rut) {
        this.id = id;
        this.rut = rut;
    }

    public AdministradorSedeDto() {
        this.id = 0;
        this.rut = "";
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

}
