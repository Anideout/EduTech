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

import com.edutech.edutech.dto.SedeDto;
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

    @PutMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, @RequestBody Sede sede) {
        return sedeService.modificar(id, sede);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return sedeService.eliminar(id);
    }

    @PostMapping("/profesor")
    public String asignaProfesor(@RequestBody SedeDto dto) {
        return sedeService.profesor(dto);
    }

    @PostMapping("/administrador/")
    public String asignarAdmin(@RequestBody SedeDto dto) {
        return sedeService.admin(dto);
    }

}