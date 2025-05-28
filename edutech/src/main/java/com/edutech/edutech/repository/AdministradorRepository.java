//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, String> {

    boolean existsByRut(String rut);

    Administrador findByRut(String rut);

}
