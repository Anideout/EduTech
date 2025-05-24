package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Sede;
import com.edutech.edutech.repository.SedeRepository;

@Service
public class SedeService {
    @Autowired
    private SedeRepository sedeRepository;

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

}
