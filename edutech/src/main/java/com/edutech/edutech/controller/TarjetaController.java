package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Tarjeta;
import com.edutech.edutech.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping("/{email}/{id}")
    public String almacenar(@RequestBody Tarjeta tarjeta, @PathVariable String email, @PathVariable int id) {
        return tarjetaService.almacenar(tarjeta, email, id);
    }

    @GetMapping
    public List<Tarjeta> listar() {
        return tarjetaService.listar();
    }

    @DeleteMapping("/eliminar/{nroTarjeta}")
    public String eliminar(@PathVariable String nroTarjeta) {
        return tarjetaService.eliminar(nroTarjeta);
    }
}
