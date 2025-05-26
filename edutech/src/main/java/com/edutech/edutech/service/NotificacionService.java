package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "notificacion almacenada con exito!";
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public String actualizarnotificacion(int id, Notificacion notificacionActualizado) {
        Notificacion notificacion = notificacionRepository.findById(id);
        if (notificacion != null) {
            notificacion.setMensaje(notificacionActualizado.getMensaje());
            notificacion.setFecha(notificacionActualizado.getFecha());
            notificacionRepository.save(notificacion);
            return "notificacion actualizado con exito!";
        } else {
            return "rut del notificacion no existe!";
        }
    }

    // @DeleteMapping("/Personas/{rut}")
    public Map<String, Boolean> eliminarNotificacion(int id) {
        Notificacion notificacion = notificacionRepository.findById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (notificacion != null) {
            notificacionRepository.delete(notificacion);
            respuesta.put("notificacion eliminado", Boolean.TRUE);

        } else {
            respuesta.put("notificacion no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }
}
