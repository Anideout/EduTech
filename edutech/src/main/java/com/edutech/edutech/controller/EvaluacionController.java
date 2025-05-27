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

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.service.EvaluacionService;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    @PostMapping
    public String almacenar(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.almacenar(evaluacion);
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @GetMapping("/buscar/{nombre}")
    public List<Evaluacion> buscar(@PathVariable String nombre) {
        return evaluacionService.buscar(nombre);
    }
    
    @PutMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, @RequestBody Evaluacion evaluacionModificada) {
    return evaluacionService.modificar(id, evaluacionModificada);
    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, Boolean> eliminar(@PathVariable int id) {
        return evaluacionService.eliminar(id);
    }

}
