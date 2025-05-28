//creado por Matías Borquez

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

import com.edutech.edutech.dto.AsignarUsuarioDto;
import com.edutech.edutech.dto.UsuarioModificarDto;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public String almacenar(@RequestBody Usuario usuario) {
        return usuarioService.almacenar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{email}")
    public List<Usuario> buscar(@PathVariable String email) {
        return usuarioService.buscar(email);
    }

    @PutMapping("/modificar/{email}")
    public String modificar(@PathVariable String email, @RequestBody Usuario usuario) {
        return usuarioService.modificarUsuario(email, usuario);
    }

    @DeleteMapping("/eliminar/{email}")
    public Map<String, Boolean> eliminar(@PathVariable String email) {
        return usuarioService.eliminarUsuario(email);
    }

    // -------------------ASIGNACIONES--------------------------

    @PostMapping("/asignar/{email}/{rut}")
    public String asignarPersona(@PathVariable String email, @PathVariable String rut) {
        return usuarioService.almacenarPersona(email, rut);

    }

    @PostMapping("/asistencia/{email}/{id}")
    public String asignarAsistencia(@PathVariable String email, @PathVariable int id) {
        return usuarioService.asignarAsistencia(email, id);
    }

    @PostMapping("/notificacion/{email}/{id}")
    public String asignarNotificacion(@PathVariable String email, @PathVariable int id) {
        return usuarioService.asignarNotificacion(email, id);
    }

    @PostMapping("/resenia/{email}/{id}")
    public String asignarResenia(@PathVariable String email, @PathVariable int id) {
        return usuarioService.almacenarResenia(email, id);
    }

    @PostMapping("/inscripcion/{email}/{id}")
    public String asignarInscripcion(@PathVariable String email, @PathVariable Integer id) {
        return usuarioService.asignarInscripcion(email, id);
    }

    // --------------------- DTO ------------------------

    @PostMapping("/persona")
    public String persona(@RequestBody AsignarUsuarioDto dto) {
        return usuarioService.persona(dto);

    }

    @PostMapping("/asistencia")
    public String asistencia(@RequestBody AsignarUsuarioDto dto) {
        return usuarioService.asistencia(dto);
    }

    @PostMapping("/reseña")
    public String resenia(@RequestBody AsignarUsuarioDto dto) {
        return usuarioService.resenia(dto);
    }

    @PostMapping("/notificacion")
    public String notificacion(@RequestBody AsignarUsuarioDto dto) {
        return usuarioService.notificacion(dto);
    }

    @PostMapping("/inscripcion")
    public String inscripcion(@RequestBody AsignarUsuarioDto dto) {
        return usuarioService.inscripcion(dto);
    }

    @PutMapping("/modificar")
    public String modificar(@RequestBody UsuarioModificarDto dto) {
        return usuarioService.modificar(dto);
    }

   

}
