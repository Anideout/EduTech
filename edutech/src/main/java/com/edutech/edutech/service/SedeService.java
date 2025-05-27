package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Sede;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.SedeRepository;

@Service
public class SedeService {
    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    AdministradorRepository administradorRepository;
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

    public String asignarSedeAdministrador(int id, String rut) {
        if (!sedeRepository.existsById(id)) {
            return "La sede ingresada no existe";
        } else if (!profesorRepository.existsByRut(rut)) {
            return "El administrador ingresado no existe";
        } else {
            Sede sede = sedeRepository.findById(id).orElse(null);
            Administrador administrador = administradorRepository.findByRut(rut);

            sede.setAdministrador(administrador);
            sedeRepository.save(sede);

            administrador.setSede(sede);
            administradorRepository.save(administrador);
            return "Administrador asignado correctamente a la sede";
        }
    }

    public String modificar(int id, Sede sedeActualizado) {
        if (!sedeRepository.existsById(id)) {
            return "la sede no existe";
        } else {
            sedeActualizado.setNombre(sedeActualizado.getNombre());
            sedeRepository.save(sedeActualizado);
            return "Sede modificada con exito";
        }
    }

    public Map<String, Boolean> eliminar(int id) {
        Map<String, Boolean> respuesta = new HashMap<>();
        if (!sedeRepository.existsById(id)) {
            respuesta.put("Sede no existe", Boolean.FALSE);
        } else {
            sedeRepository.deleteById(id);
            respuesta.put("Sede eliminada", Boolean.TRUE);
        }
        return respuesta;

    }
}
