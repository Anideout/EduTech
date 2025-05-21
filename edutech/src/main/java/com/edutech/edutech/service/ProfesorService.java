package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public String almacenar(Profesor profesor) {
        Profesor validacion = profesorRepository.findByRut(profesor.getRut());
        if (validacion != null) {
            return "error: profesor con este RUT ya existe";
        } else {
            profesorRepository.save(profesor);
            return "profesor almacenada con exito";
        }
    }

    public List<Profesor> listar() {
        return profesorRepository.findAll();
    }

    public List<Profesor> buscar(String nombre) {
        return profesorRepository.findByNombreContaining(nombre);
    }

}
