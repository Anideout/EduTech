package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    /*
     * @PostMapping
     * public String almacenar(@RequestBody Inscripcion inscripcion) {
     * return inscripcionService.almacenar(inscripcion);
     * }
     */
    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

}
