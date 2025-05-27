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

import com.edutech.edutech.model.FormaPago;
import com.edutech.edutech.service.FormaPagoService;

@RestController
@RequestMapping("/formasDePago")
public class FPagoController {
    @Autowired
    private FormaPagoService formaPagoService;

    @PostMapping
    public String almacenar(@RequestBody FormaPago pago) {
        return formaPagoService.almacenar(pago);
    }

    @GetMapping
    public List<FormaPago> listar() {
        return formaPagoService.listar();
    }

    @PutMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, @RequestBody FormaPago pagoModificado) {
        return formaPagoService.modificar(id, pagoModificado);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return formaPagoService.eliminar(id);
    }
}
