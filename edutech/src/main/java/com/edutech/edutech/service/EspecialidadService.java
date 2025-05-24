package com.edutech.edutech.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    public String almacenar(Especialidad especialidad) {
        Especialidad validacion = especialidadRepository.findByNombre(especialidad.getNombre());
        if (validacion != null) {
            return "error: especialidad ya existe!";
        } else {
            especialidadRepository.save(especialidad);
            return "Especialidad " + especialidad.getNombre() + " almacenada con exito!";
        }
    }

    public List<Especialidad> listar() {
        return especialidadRepository.findAll();
    }

    public List<Especialidad> buscar(String nombre) {
        return especialidadRepository.findByNombreContaining(nombre);
    }
}
