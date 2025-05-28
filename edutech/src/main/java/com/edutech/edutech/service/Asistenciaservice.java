//Creado Por MATIAS BORQUEZ

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.repository.AsistenciaRepository;

@Service
public class Asistenciaservice {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public String almacenar(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
        return "Asistencia guardada correctamente";
    }

    public List<Asistencia> listar() {
        return asistenciaRepository.findAll();
    }

    public String actualizarAsistencia(int id, Asistencia asistenciaActualizado) {
        if (!asistenciaRepository.existsById(id)) {
            return "asistencia no existe";
        } else {
            Asistencia asistencia = asistenciaRepository.findById(id);
            // Actualizar los campos de la asistencia
            asistencia.setId(id);
            asistencia.setNombre(asistenciaActualizado.getNombre());
            asistencia.setPorcentaje(asistenciaActualizado.getPorcentaje());
            asistencia.setFecha(asistenciaActualizado.getFecha());
            asistenciaRepository.save(asistencia);
            return "Asistencia actualizado con exito!";
        }
    }

    // @DeleteMapping("/Personas/{rut}")
    public Map<String, Boolean> eliminarAsistencia(int id) {
        Asistencia asistencia = asistenciaRepository.findById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (asistencia != null) {
            asistenciaRepository.delete(asistencia);
            respuesta.put("asistencia eliminada", Boolean.TRUE);

        } else {
            respuesta.put("Asistencia no encontrada", Boolean.FALSE);
        }
        return respuesta;
    }

}
