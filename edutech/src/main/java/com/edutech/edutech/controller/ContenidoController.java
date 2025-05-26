package com.edutech.edutech.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Contenido;
import com.edutech.edutech.service.ContenidoService;

@RestController
@RequestMapping("/contenido")
public class ContenidoController {
    @Autowired
    public ContenidoService contenidoService;

    @PostMapping
    public String almacenar(@RequestBody Contenido contenido) {
        return contenidoService.almacenar(contenido);
    }

    @GetMapping
    public List<Contenido> listar() {
        return contenidoService.listar();
    }

    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Contenido contenido) {
        return contenidoService.actualizarContenido(id, contenido);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return contenidoService.eliminarcontenido(id);
    }

}
