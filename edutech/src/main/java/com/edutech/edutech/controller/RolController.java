//CREADO POR MATIAS BORQUEZ

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

import com.edutech.edutech.dto.RolDto;
import com.edutech.edutech.model.Rol;
import com.edutech.edutech.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    public String almacenar(@RequestBody Rol rol) {
        return rolService.almacenar(rol);
    }

    @PostMapping("/buscar{nombre}")
    public String buscar(@PathVariable String nombre) {
        return rolService.buscar(nombre);
    }

    @GetMapping
    public List<Rol> listar() {
        return rolService.listar();
    }

    @PutMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, @RequestBody Rol rolActualizado) {
        return rolService.modificar(id, rolActualizado);
    }

    @PutMapping("/modificar")
    public String modificar(@RequestBody RolDto dto) {
        return rolService.modificar(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return rolService.eliminar(id);
    }

    @PostMapping("/usuario")
    public String usuario(@RequestBody RolDto dto) {
        return rolService.usuario(dto);
    }

    @PostMapping("profesor")
    public String profesor(@RequestBody RolDto dto) {
        return rolService.profesor(dto);
    }

    @PostMapping("/administrador")
    public String administrador(@RequestBody RolDto dto) {
        return rolService.admin(dto);
    }
}
