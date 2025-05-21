package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Usuario usuario) {
        Usuario validacion = usuarioRepository.findByEmail(usuario.getEmail());
        if (validacion != null) {
            return "error: persona con este RUT ya existe";
        } else { // Si no se encuentra ninguna persona con este RUT
            usuarioRepository.save(usuario);
            return "persona almacenada con exito";
        }
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscar(String email) {
        return usuarioRepository.findByEmailContaining(email);
    }

}
