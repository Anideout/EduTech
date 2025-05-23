package com.edutech.edutech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.UsuarioDto;
import com.edutech.edutech.dto.UsuarioPerfilDto;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.model.Perfil;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.PerfilRepository;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public String almacenar(Usuario usuario) {
        Usuario validacion = usuarioRepository.findByEmail(usuario.getEmail());
        if (validacion != null) {
            return "error: usuario con este email ya existe";
        } else {
            usuarioRepository.save(usuario);
            return "usuario " + usuario.getEmail() + " almacenada con exito";
        }
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscar(String email) {
        return usuarioRepository.findByEmailContaining(email);
    }

    public String usuarioAsignarCurso(String rut, int sigla) {
        if (!usuarioRepository.existsById(rut)) {
            return "usuario no existe!";
        }
        if (!cursoRepository.existsById(sigla)) {
            return "Curso ingresado no existe!";
        }

        // obtenemos el curso y el usuario
        Curso curso = cursoRepository.findById(sigla).get();
        Usuario usuario = usuarioRepository.findById(rut).get();

        // valiamos que el alumno no esté inscrito en ese curso
        if (usuario.getCursos().contains(curso)) {
            return "el alumno ya está en el curso";
        }

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);
        return "curso asignado correctam!ente";

    }

    public List<UsuarioDto> buscarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDto(usuario.getEmail(), usuario.getPersona()))
                .collect(Collectors.toList());

    }

    public String usuarioAsignarPerfil(String rut, String tag) {
        if (!usuarioRepository.existsById(rut)) {
            return "usuario no existe!";
        }
        if (!perfilRepository.existsById(tag)) {
            return "perfil ingresado no existe!";
        }

        // obtenemos el curso y el usuario
        Perfil perfil = perfilRepository.findById(tag).get();
        Usuario usuario = usuarioRepository.findById(rut).get();

        // valiamos que el alumno no esté inscrito en ese curso
        if (usuario.getPerfiles().contains(perfil)) {
            return "el alumno ya tiene este perfil asignado";
        }

        usuario.getPerfiles().add(perfil);
        usuarioRepository.save(usuario);
        return "perfil asignado correctam!ente";
    }

    public String asignarEspecialidad(UsuarioPerfilDto dto) {
        if (!usuarioRepository.existsById(dto.getRut())) {
            return "usuario no existe!";
        }
        if (!perfilRepository.existsById(dto.getTag())) {
            return "perfil ingresado no existe!";
        }

        // obtenemos el curso y el usuario
        Perfil perfil = perfilRepository.findById(dto.getTag()).get();
        Usuario usuario = usuarioRepository.findById(dto.getRut()).get();

        // valiamos que el alumno no esté inscrito en ese curso
        if (usuario.getPerfiles().contains(perfil)) {
            return "el alumno ya tiene este perfil asignado";
        }

        usuario.getPerfiles().add(perfil);
        usuarioRepository.save(usuario);
        return "perfil asignado correctam!ente";
    }

}
