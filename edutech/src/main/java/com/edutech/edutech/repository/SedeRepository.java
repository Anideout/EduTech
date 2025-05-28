//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
    Sede findByNombre(String nombre);

}
