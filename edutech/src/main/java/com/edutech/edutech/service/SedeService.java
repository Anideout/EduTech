package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Sede;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.SedeRepository;

@Service
public class SedeService {
    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public String almacenar(Sede sede) {
        if (sedeRepository.findByNombre(sede.getNombre()) == null) {
            sedeRepository.save(sede);
            return "Sede almacenada con exito";
        } else {
            return "La sede ya existe";
        }
    }

    public List<Sede> listar() {
        return sedeRepository.findAll();
    }

    public String asignarProfesorSede(int id, String rut) {
        if (!sedeRepository.existsById(id)) {
            return "La sede ingresada no existe";
        } else if (!profesorRepository.existsByRut(rut)) {
            return "el profesor ingresado no existe";
        } else {
            Sede sede = sedeRepository.findById(id).orElse(null);
            Profesor profesor = profesorRepository.findByRut(rut);

            sede.getProfesores().add(profesor);
            sedeRepository.save(sede);

            profesor.getSedes().add(sede);
            profesorRepository.save(profesor);
            return "Profesor asignado correctamente al curso";
        }
    }

}
