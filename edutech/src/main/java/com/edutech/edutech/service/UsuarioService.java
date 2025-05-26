package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.UsuarioDto;
import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.model.Persona;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    /*
     * @Autowired
     * private CursoRepository cursoRepository;
     * 
     * @Autowired
     * private PerfilRepository perfilRepository;
     */
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

    /*
     * public String usuarioAsignarCurso(String rut, int sigla) {
     * if (!usuarioRepository.existsById(rut)) {
     * return "usuario no existe!";
     * }
     * if (!cursoRepository.existsById(sigla)) {
     * return "Curso ingresado no existe!";
     * }
     * 
     * // obtenemos el curso y el usuario
     * Curso curso = cursoRepository.findById(sigla).get();
     * Usuario usuario = usuarioRepository.findById(rut).get();
     * 
     * // valiamos que el alumno no esté inscrito en ese curso
     * if (usuario.getCursos().contains(curso)) {
     * return "el alumno ya está en el curso";
     * }
     * 
     * usuario.getCursos().add(curso);
     * usuarioRepository.save(usuario);
     * return "curso asignado correctam!ente";
     * 
     * }
     */
    public List<UsuarioDto> buscarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDto(usuario.getEmail(), usuario.getPersona()))
                .collect(Collectors.toList());

    }

    public String asignarAsistencia(String rut, int id) {
        if (!usuarioRepository.existsById(rut)) {
            return "El rut ingresado no existe!";
        } else if (!asistenciaRepository.existsById(id)) {
            return "La asistencia no existe";
        } else {
            Usuario usuario = usuarioRepository.findById(rut).get();
            Asistencia asistencia = asistenciaRepository.findById(id);

            usuario.getAsistencia().add(asistencia);
            usuarioRepository.save(usuario);

            return "Asistencia asignada con exito al usuario " + usuario.getEmail() + " con exito";
        }
    }

    public String ActualizarUsuario(String email, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setPersona(usuarioActualizado.getPersona());

            usuarioRepository.save(usuario);
            return "usuario actualizado con exito!";
        } else {
            return "email del usuario no existe!";
        }

    }

    public Map<String, Boolean> eliminarUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (usuario != null) {
            // if para desvincular el usuario de la persona asignada
            if (usuario.getPersona() != null) {
                Persona persona = usuario.getPersona();
                persona.setUsuario(null);// desvincula la relacion en persona
                personaRepository.save(persona);
                usuario.setPersona(null);// desvincula la relacion en usuario
            }
            usuarioRepository.delete(usuario);
            respuesta.put("usuario eliminado con exito!", Boolean.TRUE);
        } else {
            respuesta.put("usuario no encontrado....", Boolean.FALSE);
        }
        return respuesta;
    }

    public String almacenarPersona(String email, String rut) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Persona persona = personaRepository.findByRut(rut);
        if (persona == null) {
            return "la persona con este rut no existe";
        } else if (usuario == null) {
            return "usuario con este email no existe";
        }
        usuario.setPersona(persona);
        persona.setUsuario(usuario);
        usuarioRepository.save(usuario);
        personaRepository.save(persona);
        return "persona y usuarios asociados con exito!";

    }

    public String asignarAsistencia(String email, Integer id) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id);
        if (usuario == null) {
            return "usuario no encontrado";
        } else if (asistencia.isEmpty()) {
            return "asistencia no encontrada";
        }
        usuario.getAsistencia().add(asistencia.get());
        asistencia.get().setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "asistencia asignada correctamente al usuario";
    }

    public String asignarNotificacion(String email, Integer id) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Optional<Notificacion> notificacion = notificacionRepository.findById(id);
        if (usuario == null) {
            return "usuario no encontrado";
        } else if (notificacion.isEmpty()) {
            return "notificacion no encontrada";
        }
        usuario.getNotificacion().add(notificacion.get());
        notificacion.get().setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "notificacion asignada correctamente al usuario";
    }

}
