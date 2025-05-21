package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.service.ProfesorService;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public String almacenar(@RequestBody Profesor profesor) {
        return profesorService.almacenar(profesor);
    }

    @GetMapping
    public List<Profesor> listar() {
        return profesorService.listar();
    }

    @GetMapping("/{nombre}")
    public List<Profesor> buscar(@PathVariable String nombre) {
        return profesorService.buscar(nombre);
    }
}