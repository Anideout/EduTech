package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.repository.AsistenciaRepository;

@Service
public class Asistenciaservice {
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public String almacenar(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
        return "Asistencia guardada correctamente";
    }

    public List<Asistencia> listar() {
        return asistenciaRepository.findAll();
    }

}
