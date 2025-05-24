package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    Especialidad findById(int id);

}
