// Creado por Sergio Puebla

package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    Profesor findByRut(String rut);

    List<Profesor> findByNombreContaining(String nombre);

    public boolean existsByRut(String rut);
}
