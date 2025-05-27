package com.edutech.edutech.repository;
//Creado por Sergio Puebla
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {

    Curso findBySigla(String sigla);

    List<Curso> findByNombreContaining(String nombre);

    boolean existsBySigla(String sigla);

}
