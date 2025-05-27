package com.edutech.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    Notificacion findById(int id);
}
