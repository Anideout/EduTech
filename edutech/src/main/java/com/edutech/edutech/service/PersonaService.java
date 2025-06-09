//Creado por Matías Borquez

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.PersonaDTO;
import com.edutech.edutech.model.Persona;
import com.edutech.edutech.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public String almacenar(Persona persona) {
        if (personaRepository.existsById(persona.getRut())) {
            return "NOK"; // Persona ya existe
        }
        personaRepository.save(persona);
        return "OK"; // Persona almacenada correctamente
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

    public String modificar(PersonaDTO dto) {
        Persona persona = personaRepository.findByRut(dto.getRut());
        if (persona != null) {
            persona.setNombre(dto.getNombre());
            persona.setApellido(dto.getApellido());
            persona.setDireccion(dto.getDireccion());
            personaRepository.save(persona);

            return "persona actualizada correctamente";
        } else {
            return " persona con ese rut no existe";
        }
    }

}
