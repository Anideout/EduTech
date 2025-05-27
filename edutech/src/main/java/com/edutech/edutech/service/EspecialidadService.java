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
        Especialidad validacion = especialidadRepository.findByNombre(especialidad.getNombre());
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

    public String modificar(String nombre, Especialidad especialidadModificado) {
        Especialidad especialidad = especialidadRepository.findByNombre(nombre);
        if(especialidad == null) {
            return "especialidad no encontrada";
        }
        especialidad.setNombre(especialidadModificado.getNombre());
        especialidadRepository.save(especialidad);
        return "especialidad guardada con exito";
    }

    public Map<String, Boolean> eliminar(String nombre ) {
        Especialidad especialidad = especialidadRepository.findByNombre(nombre);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (especialidad != null) {
            if(especialidad.getProfesores() != null) {
                for(Profesor profesor: especialidad.getProfesores()) {
                    profesor.setEspecialidad(null);
                    profesorRepository.save(profesor);
                }
                especialidad.getProfesores().clear();
            }

            //eliminar la especliada
            especialidadRepository.delete(especialidad);
            respuesta.put("especialidad eliminada con exito!", Boolean.TRUE);
        }else {
            respuesta.put("especialiadad no encontrada...", Boolean.FALSE);
        }
        return respuesta;
            
    }
}
