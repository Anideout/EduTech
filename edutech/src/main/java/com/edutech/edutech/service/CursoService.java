package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public String almacenar(Curso curso) {
        Curso validacion = cursoRepository.findByNombre(curso.getNombre());
        if (validacion != null) {
            return "El curso ya existe";
        } else {
            cursoRepository.save(curso);
            return "Curso " + curso.getNombre() + " se ha almacenado";
        }
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public List<Curso> buscar(String nombre) {
        return cursoRepository.findByNombreContaining(nombre);
    }

}
