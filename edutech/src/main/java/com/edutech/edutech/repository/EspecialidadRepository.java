//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    Especialidad findById(int id);

    List<Especialidad> findByNombreContaining(String nombre);

}
