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

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public String almacenar(@RequestBody Curso curso) {
        return cursoService.almacenar(curso);
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @GetMapping("/{nombre}")
    public List<Curso> buscar(@PathVariable String nombre) {
        return cursoService.buscar(nombre);
    }

    

    @PutMapping("/modificar/{sigla}")
    public String modificarCurso(@PathVariable String sigla, @RequestBody Curso curso){
        return cursoService.actualizarCurso(sigla, curso);
    }

    @DeleteMapping("/eliminar/{sigla}")
    public Map<String, Boolean> eliminarCurso(@PathVariable String sigla) {
        return cursoService.eliminarCurso(sigla);
    }


    // ----------------------Asignaciones----------------------

    @PostMapping("/asignar/{sigla}/{id}")
    public String asignarEvaluacion(@PathVariable String sigla, @PathVariable int id) {
        return cursoService.asignarEvaluacion(sigla, id);
    }

    @PostMapping("/asignarProfesor/{sigla}/{rut}")
    public String asignarProfesor(@PathVariable String sigla, @PathVariable String rut) {
        return cursoService.AsignarProfesorCurso(sigla, rut);
    }

    @PostMapping("/asignarUsuario/{sigla}/{email}")
    public String asignarUsuario(@PathVariable String sigla, @PathVariable String email) {
        return cursoService.AsignarUsuarioCurso(sigla, email);
    }

    @PostMapping("/asignarContenido/{sigla}/{id}")
    public String asignarContenido(@PathVariable String sigla, @PathVariable int id) {
        return cursoService.asignarContenido(sigla, id);
    }

}
