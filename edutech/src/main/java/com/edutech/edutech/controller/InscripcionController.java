//CREADO POR MATIAS BORQUEZ

package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public String almacenar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.almacenar(inscripcion);
    }

    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

    @PutMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.modificar(id, inscripcion);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        return inscripcionService.eliminar(id);
    }
}
