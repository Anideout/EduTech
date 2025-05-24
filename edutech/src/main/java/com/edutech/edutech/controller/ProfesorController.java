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

import com.edutech.edutech.dto.ProfesorEspecialidadDto;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.service.ProfesorService;

@RestController
@RequestMapping("/profesores")
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

    @PostMapping("/asignar/{rut}/{id}")
    public String asignarEspecialidad(@PathVariable String rut, @PathVariable int id) {
        return profesorService.asignarEspecialidad(rut, id);
    }

    @PostMapping("/asignar")
    public String asignarEspecialidad(@RequestBody ProfesorEspecialidadDto dto) {
        return profesorService.asignarEspecialidad(dto);
    }

    @PutMapping("/actualizar/{rut}")
    public String actualizar(@PathVariable String rut, @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(rut, profesor);
    }

    @DeleteMapping("/eliminar/{rut}")
    public Map<String, Boolean> eliminarProfesor(@PathVariable String rut) {
        return profesorService.eliminarProfesor(rut);
    }

}