package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Persona;
import com.edutech.edutech.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public String almacenar(Persona persona) {
        Persona validacion = personaRepository.findByRut(persona.getRut());
        if (validacion != null) {
            return "error: persona con este RUT ya existe";
        } else { // Si no se encuentra ninguna persona con este RUT
            personaRepository.save(persona);
            return "persona almacenada con exito";
        }
    }

    public List<Persona> listar() {
        return personaRepository.findAll();
    }

    public List<Persona> buscar(String nombre) {
        return personaRepository.findByNombreContaining(nombre);
    }

}
