package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // @PutMapping("/personas/{rut}")
    public String actualizarPersona(String rut, Persona personaActualizada) {
        Persona persona = personaRepository.findByRut(rut);
        if (persona != null) {
            persona.setNombre(personaActualizada.getNombre());
            persona.setApellido(personaActualizada.getApellido());
            persona.setDireccion(personaActualizada.getDireccion());
            persona.setUsuario(personaActualizada.getUsuario());
            personaRepository.save(persona);

            return "persona actualizada correctamente";
        } else {
            return " persona con ese rut no existe";
        }
    }

    // @DeleteMapping("/eliminar/{rut}")
    public Map<String, Boolean> eliminarPersona(String rut) {
        Persona persona = personaRepository.findByRut(rut);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (persona != null) {
            personaRepository.delete(persona);
            respuesta.put("persona eliminada", Boolean.TRUE);

        } else {
            respuesta.put("persona no encontrada", Boolean.FALSE);
        }
        return respuesta;
    }

}
