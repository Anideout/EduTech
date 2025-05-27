package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edutech.edutech.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    Tarjeta findById(int id);
}
