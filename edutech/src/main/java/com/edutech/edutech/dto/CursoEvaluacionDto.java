package com.edutech.edutech.dto;

public class CursoEvaluacionDto {

    private String sigla;
    private int id;

    public CursoEvaluacionDto(String sigla, int id) {
        this.sigla = sigla;
        this.id = id;
    }

    public CursoEvaluacionDto() {
        this.sigla = "";
        this.id = 0;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
