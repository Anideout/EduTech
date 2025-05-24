package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    Persona findByRut(String rut);

    List<Persona> findByNombreContaining(String nombre);

    public boolean existsByRut(String rut);

}
