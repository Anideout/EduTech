package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.service.Asistenciaservice;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {
    @Autowired
    private Asistenciaservice asistenciaService;

    @PostMapping
    public String almacenar(@RequestBody Asistencia asistencia) {
        return asistenciaService.almacenar(asistencia);
    }

    @GetMapping
    public List<Asistencia> listar() {
        return asistenciaService.listar();
    }

}
