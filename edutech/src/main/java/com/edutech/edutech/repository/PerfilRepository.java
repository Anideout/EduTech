package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, String> {
    Perfil findByTag(String tag);

    List<Perfil> findByNombreContaining(String nombre);
}
