package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    Asistencia findById(int id);
}
