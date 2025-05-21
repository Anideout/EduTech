package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Persona;
import com.edutech.edutech.service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public String almacenar(@RequestBody Persona persona) {
        return personaService.almacenar(persona);
    }

    @GetMapping
    public List<Persona> listar() {
        return personaService.listar();
    }

    @GetMapping("/{nombre}")
    public List<Persona> buscar(@PathVariable String nombre) {
        return personaService.buscar(nombre);
    }
}
