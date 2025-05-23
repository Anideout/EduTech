package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return "el rut ingresado ya existe! ";

        } else {
            profesorRepository.save(profesor);
            return "profesor almacenado";
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
            Especialidad especialidad = especialidadRepository.findById(id).get();

            profesor.setEspecialidad(especialidad);
            profesorRepository.save(profesor);

            return "Especialidad: " + especialidad.getNombre() + " asignada al profesor: " + profesor.getNombre() + " "
                    + profesor.getApellido();
        }
    }

}
