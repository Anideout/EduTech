//Creada por Matías Borquez

package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.repository.InscripcionRepository;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    public String almacenar(Inscripcion inscripcion) {
        if (inscripcionRepository.existsById(inscripcion.getId())) {
            return "inscripcion ya existe!";
        } else {
            inscripcionRepository.save(inscripcion);
            return "Inscripcion almacenada";
        }
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public String modificar(int id, Inscripcion inscripcionModificada) {
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElse(null);
        if (inscripcion == null) {
            return "inscripcion no encontrada";
        }
        inscripcion.setNombre(inscripcionModificada.getNombre());
        inscripcion.setEstado(inscripcionModificada.getEstado());
        inscripcion.setFechaIngreso(inscripcionModificada.getFechaIngreso());
        inscripcionRepository.save(inscripcion);
        return "Inscripcion modificada con exito";
    }

    public String eliminar(int id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElse(null);
        if (inscripcion == null) {
            return "inscripcion no encontrada";
        }
        inscripcionRepository.delete(inscripcion);
        return "inscripcion eliminada con exito!";
    }
}
