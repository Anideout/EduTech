package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Perfil;
import com.edutech.edutech.repository.PerfilRepository;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public String almacenar(Perfil perfil) {
        if (perfilRepository.findByNombre(perfil.getNombre()) == null) {
            perfilRepository.save(perfil);
            return "perfil " + perfil.getNombre() + " almacenado correctamente";
        } else {
            return "Perfil " + perfil.getNombre() + " ya existe";
        }
    }

    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    public List<Perfil> buscar(String nombre) {
        return perfilRepository.findByNombreContaining(nombre);
    }
}
