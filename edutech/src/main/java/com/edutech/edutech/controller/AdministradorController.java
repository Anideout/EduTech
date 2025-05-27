package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    AdministradorService administradorService;

    public String almacenar(@RequestBody Administrador administrador) {
        return administradorService.almacenar(administrador);
    }

    public List<Administrador> listar() {
        return administradorService.listar();
    }

    public String modificar(@PathVariable String rut, @RequestBody Administrador administrador) {
        return administradorService.modificar(rut, administrador);
    }

    public String asignarRol(@PathVariable String rut, @RequestBody int id) {
        return administradorService.asignarRol(rut, id);
    }
}
