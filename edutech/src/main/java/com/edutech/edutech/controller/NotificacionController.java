package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public String almacenar(@RequestBody Notificacion notificacion) {
        return notificacionService.almacenar(notificacion);
    }

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }
}
