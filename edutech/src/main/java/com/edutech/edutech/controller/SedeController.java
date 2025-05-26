package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Sede;
import com.edutech.edutech.service.SedeService;

@RestController
@RequestMapping("/Sedes")
public class SedeController {
    @Autowired
    private SedeService sedeService;

    @PostMapping
    public String almacenar(@RequestBody Sede sede) {
        return sedeService.almacenar(sede);
    }

    @GetMapping
    public List<Sede> listar() {
        return sedeService.listar();
    }

    @PostMapping("/profesor/{id}/{rut}")
    public String asignaProfesor(@PathVariable int id, @PathVariable String rut) {
        return sedeService.asignarProfesorSede(id, rut);
    }
}