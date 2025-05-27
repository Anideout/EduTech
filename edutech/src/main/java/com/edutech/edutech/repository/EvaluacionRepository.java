package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

    Evaluacion findByNombre(String nombre);

    List<Evaluacion> findByNombreContaining(String string);
}
