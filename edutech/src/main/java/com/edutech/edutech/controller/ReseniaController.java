package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.service.ReseniaService;

@RestController
@RequestMapping("/resenias")
public class ReseniaController {
    @Autowired
    private ReseniaService reseniaService;

    @PostMapping
    public String almacenar(@RequestBody Resenia resenia) {
        return reseniaService.almacenar(resenia);
    }

    @GetMapping
    public List<Resenia> listar() {
        return reseniaService.listar();
    }

}
