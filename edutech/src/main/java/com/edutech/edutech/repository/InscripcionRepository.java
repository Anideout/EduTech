package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    Inscripcion findByid(Integer id);

    List<Inscripcion> findByIdContaining(Integer id);
}
