//CREADO POR PROFESOR

package com.edutech.edutech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rut")
@Entity
public class Profesor {
    @Id
    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "sede_profesor", joinColumns = @JoinColumn(name = "profesor_rut", referencedColumnName = "rut"), inverseJoinColumns = @JoinColumn(name = "sede_id", referencedColumnName = "id"))

    private List<Sede> sedes;

    @ManyToMany
    @JoinTable(name = "profesor_curso", joinColumns = @JoinColumn(name = "profesor_rut", referencedColumnName = "rut"), inverseJoinColumns = @JoinColumn(name = "curso_sigla", referencedColumnName = "sigla"))
    private List<Curso> cursos;

    public Profesor() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.contrasena = "";

    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    /**
     * @return Rol return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
