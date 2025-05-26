package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public String actualizarAsistencia(Integer id, Asistencia asistenciaActualizado) {
        Optional<Asistencia> asistenciaOpt = asistenciaRepository.findById(id);
        if (asistenciaOpt.isPresent()) {
            Asistencia asistencia = asistenciaOpt.get();
            // Actualizar los campos de la asistencia
            asistencia.setId(id);
            asistencia.setNombre(asistenciaActualizado.getNombre());
            asistencia.setPorcentaje(asistenciaActualizado.getPorcentaje());
            asistencia.setFecha(asistenciaActualizado.getFecha());
            asistenciaRepository.save(asistencia);
            return "Asistencia actualizado con exito!";
        } else {
            return "id de la asistencia no existe!!";
        }
    }

    // @DeleteMapping("/Personas/{rut}")
    public Map<String, Boolean> eliminarAsistencia(Integer id) {
        Optional<Asistencia> asistenciaOpt = asistenciaRepository.findById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (asistenciaOpt.isPresent()) {
            asistenciaRepository.delete(asistenciaOpt.get());
            respuesta.put("asistencia eliminada", Boolean.TRUE);

        } else {
            respuesta.put("Asistencia no encontrada", Boolean.FALSE);
        }
        return respuesta;
    }

}
