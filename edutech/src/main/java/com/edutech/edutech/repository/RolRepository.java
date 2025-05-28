//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    Rol findByNombreContaining(String nombre);

}
