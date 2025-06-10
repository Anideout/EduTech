// Creado por Sergio Puebla

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.ProfesorEspecialidadDto;
import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.EspecialidadRepository;
import com.edutech.edutech.repository.ProfesorRepository;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public String almacenar(Profesor profesor) {
        if (profesorRepository.existsById(profesor.getRut())) {
            return "NOK";

        } else {
            profesorRepository.save(profesor);
            return "OK";
        }
    }

    public List<Profesor> listar() {
        return profesorRepository.findAll();
    }

    public List<Profesor> buscar(String nombre) {
        return profesorRepository.findByNombreContaining(nombre);
    }

    public String asignarEspecialidad(String rut, int id) {
        if (!profesorRepository.existsById(rut)) {
            return "El rut ingresado no existe!";
        } else if (!especialidadRepository.existsById(id)) {
            return "La especiadad no existe";
        } else {
            Profesor profesor = profesorRepository.findById(rut).get();
            Especialidad especialidad = especialidadRepository.findById(id);

            profesor.setEspecialidad(especialidad);
            profesorRepository.save(profesor);

            return "Especialidad: " + especialidad.getNombre() + " asignada al profesor: " + profesor.getNombre() + " "
                    + profesor.getApellido();
        }
    }

    public String asignarEspecialidadDto(ProfesorEspecialidadDto dto) {
        if (!profesorRepository.existsById(dto.getRut())) {
            return "El rut ingresado no existe!";
        } else if (!especialidadRepository.existsById(dto.getId())) {
            return "La especiadad no existe";
        } else {
            Profesor profesor = profesorRepository.findById(dto.getRut()).get();
            Especialidad especialidad = especialidadRepository.findById(dto.getId());

            profesor.setEspecialidad(especialidad);
            profesorRepository.save(profesor);

            return "Especialidad: " + especialidad.getNombre() + " asignada al profesor: " + profesor.getNombre() + " "
                    + profesor.getApellido();
        }
    }

    public String actualizarProfesor(String rut, Profesor profesorActualizado) {
        Profesor profesor = profesorRepository.findByRut(rut);
        if (profesor != null) {
            profesor.setNombre(profesorActualizado.getNombre());
            profesor.setApellido(profesorActualizado.getApellido());
            profesor.setDireccion(profesorActualizado.getDireccion());
            profesor.setContrasena(profesorActualizado.getContrasena());
            profesorRepository.save(profesor);
            return "profesor actualizado con exito!";
        } else {
            return "rut del profesor no existe!";
        }
    }

    // @DeleteMapping("/Personas/{rut}")
    public Map<String, Boolean> eliminarProfesor(String rut) {
        Profesor profesor = profesorRepository.findByRut(rut);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (profesor != null) {
            profesorRepository.delete(profesor);
            respuesta.put("profesor eliminado", Boolean.TRUE);

        } else {
            respuesta.put("profesor no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }

}
