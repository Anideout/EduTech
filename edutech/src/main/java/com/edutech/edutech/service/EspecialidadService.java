//CREADA POR SERGIO PUEBLA

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.EspecialidadRepository;
import com.edutech.edutech.repository.ProfesorRepository;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public String almacenar(Especialidad especialidad) {
        Especialidad validacion = especialidadRepository.findById(especialidad.getId());
        if (validacion != null) {
            return "error: especialidad ya existe!";
        } else {
            especialidadRepository.save(especialidad);
            return "Especialidad " + especialidad.getNombre() + " almacenada con exito!";
        }
    }

    public List<Especialidad> listar() {
        return especialidadRepository.findAll();
    }

    public List<Especialidad> buscar(String nombre) {
        return especialidadRepository.findByNombreContaining(nombre);
    }

    public String modificar(int id, Especialidad especialidadModificado) {
        Especialidad especialidad = especialidadRepository.findById(id);
        if (especialidad == null) {
            return "especialidad no encontrada";
        }
        especialidad.setNombre(especialidadModificado.getNombre());
        especialidadRepository.save(especialidad);
        return "especialidad modificada con exito";
    }

    

    public Map<String, Boolean> eliminar(int id) {
        Especialidad especialidad = especialidadRepository.findById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (especialidad != null) {
            if (especialidad.getProfesores() != null) {
                for (Profesor profesor : especialidad.getProfesores()) {
                    profesor.setEspecialidad(null);
                    profesorRepository.save(profesor);
                }
                especialidad.getProfesores().clear();
            }

            // eliminar la especliada
            especialidadRepository.delete(especialidad);
            respuesta.put("especialidad eliminada con exito!", Boolean.TRUE);
        } else {
            respuesta.put("especialiadad no encontrada...", Boolean.FALSE);
        }
        return respuesta;

    }
}
