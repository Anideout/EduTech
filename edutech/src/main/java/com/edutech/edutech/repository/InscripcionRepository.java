package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    Inscripcion findBySeguimiento(Integer seguimiento);
}
