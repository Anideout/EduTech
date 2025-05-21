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
        Inscripcion validacion = inscripcionRepository.findByid(inscripcion.getId());
        if (validacion != null) {
            return "Inscripcion ya existe";
        } else {
            inscripcionRepository.save(inscripcion);
            return "Inscripcion guardada";
        }
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> buscar(Integer id) {
        return inscripcionRepository.findByIdContaining(id);
    }

}
