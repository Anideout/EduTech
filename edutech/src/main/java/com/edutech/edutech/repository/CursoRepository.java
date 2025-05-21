package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Curso findByNombre(String nombre);

    List<Curso> findByNombreContaining(String nombre);

}
