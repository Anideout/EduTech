package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public String almacenar(Notificacion notificacion) {
        notificacionRepository.save(notificacion);
        return "notificacion almacenada correctamente";
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }
}
