package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.service.EspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping
    public String almacenar(@RequestBody Especialidad especialidad) {
        return especialidadService.almacenar(especialidad);
    }

    @GetMapping
    public List<Especialidad> listar() {
        return especialidadService.listar();
    }

    @GetMapping("/{nombre}")
    public List<Especialidad> buscar(@PathVariable String nombre) {
        return especialidadService.buscar(nombre);
    }
}
