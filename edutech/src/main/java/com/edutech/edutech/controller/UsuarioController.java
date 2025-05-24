package com.edutech.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/asignar/{email}/{rut}")
    public String asignar(@PathVariable String email, @PathVariable String rut) {
        return usuarioService.asignarPersona(email, rut);

    }
    // @GetMapping("/usuarios/{email}")
    // public ResponseEntity<Usuario> verDatosUsuario(@PathVariable String email) {
    // Usuario usuario = usuarioRepository.findByEmail(email)
    // .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));

    // return ResponseEntity.ok().body(usuario);

    // }

    // @PutMapping("/usuarios/{rut}")
    // public ResponseEntity<Usuario> ActualizarUsuario(@PathVariable String email,
    // @RequestBody Usuario usuarioActualizado) {
    // Usuario usuario = usuarioRepository.findByEmail(email)
    // .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
    // usuario.setEmail(usuarioActualizado.getEmail());
    // return ResponseEntity.ok(usuarioActualizado);
    // }

    // @DeleteMapping("/usuarios/{email}")
    // public Map<String, Boolean> eliminarUsuario(@PathVariable String email) {
    // Usuario usuario = usuarioRepository.findByEmail(email)
    // .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
    // usuarioRepository.delete(usuario);
    // Map<String, Boolean> respuesta = new HashMap<>();
    // respuesta.put("Usuario eliminado!", Boolean.TRUE);
    // return respuesta;
    // }

}
