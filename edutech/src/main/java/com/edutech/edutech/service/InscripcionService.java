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

    /*
     * public String almacenar(Inscripcion inscripcion) {
     * Inscripcion validacion = inscripcionRepository.findById(id);
     * if (validacion != null) {
     * return "Inscripcion ya existe";
     * } else {
     * inscripcionRepository.save(inscripcion);
     * return "Inscripcion guardada";
     * }
     * }
     */
    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

}
