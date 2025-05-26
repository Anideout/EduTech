package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.repository.ReseniaRepository;

@Service
public class ReseniaService {
    @Autowired
    private ReseniaRepository reseniaRepository;

    public String almacenar(Resenia resenia) {
        reseniaRepository.save(resenia);
        return "resenia guardada con exito!";
    }

    public List<Resenia> listar() {
        return reseniaRepository.findAll();
    }
}
