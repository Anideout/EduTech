package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public String almacenar(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
        return "evaluacion  almacenada con exito!";
    }

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

}
