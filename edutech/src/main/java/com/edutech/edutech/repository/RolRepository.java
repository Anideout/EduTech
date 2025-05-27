package com.edutech.edutech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
    List<Rol> findByDescripcionContaining(String descripcion);
    Optional<Rol> findById(Long id);
    boolean existsById(String nombre);
    Rol findByNombreContaining(String nombre);

}
