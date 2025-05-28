//CREADO POR SERGIO PUEBLA

package com.edutech.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Contenido;

public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {

    List<Contenido> findByTituloContaining(String titulo);

    Contenido findById(int id);

}
