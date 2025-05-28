//CREADO POR SERGIO PUEBLA

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

import com.edutech.edutech.dto.AdministradorRolDto;
import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    AdministradorService administradorService;

    @PostMapping
    public String almacenar(@RequestBody Administrador administrador) {
        return administradorService.almacenar(administrador);
    }

    @GetMapping
    public List<Administrador> listar() {
        return administradorService.listar();
    }

    @PutMapping("/modificar/{rut}")
    public String modificar(@PathVariable String rut, @RequestBody Administrador administrador) {
        return administradorService.modificar(rut, administrador);
    }

    @DeleteMapping("/eliminar/{rut}")
    public Map<String, Boolean> eliminar(@PathVariable String rut) {
        return administradorService.eliminar(rut);
    }

    @PostMapping("/asignarRol/{rut}/{id}")
    public String asignarRol(@PathVariable String rut, @PathVariable int id) {
        return administradorService.asignarRol(rut, id);

    }

    @PostMapping("/asignarRoll")
    public String asignarRoldto(AdministradorRolDto dto) {
        return administradorService.asignarRolDto(dto);
    }
}
