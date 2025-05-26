package com.edutech.edutech.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.service.NotificacionService;

@RestController
@RequestMapping("/notificacion")
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

    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Notificacion notificacion) {
        return notificacionService.actualizarnotificacion(id, notificacion);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return notificacionService.eliminarNotificacion(id);
    }

}
