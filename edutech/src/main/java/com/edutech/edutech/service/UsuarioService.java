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
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.model.Persona;
import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.InscripcionRepository;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.repository.ReseniaRepository;
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

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;
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

    public List<UsuarioDto> buscarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDto(usuario.getEmail(), usuario.getPersona()))
                .collect(Collectors.toList());

    }
    //modificar
    public String modificarUsuario(String email, Usuario usuarioActualizado) {
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
    //eliminar
   public Map<String, Boolean> eliminarUsuario(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    //map es una estructura de datos que almacena pares clave-valor
    //para devolver un string y boolean
    Map<String, Boolean> respuesta = new HashMap<>();
    if (usuario != null) {
        // Elimina la relación en ambos lados con cursos
        for (Curso curso : usuario.getCursos()) {
            curso.getUsuarios().remove(usuario);
            cursoRepository.save(curso);
        }
        usuario.getCursos().clear();

        usuarioRepository.save(usuario);
        usuarioRepository.delete(usuario);
        respuesta.put("usuario eliminado", Boolean.TRUE);
    } else {
        respuesta.put("usuario no encontrado", Boolean.FALSE);
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

    // ---------------------- ASIGNACIONES --------------------------------

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

    public String almacenarResenia(String email, int id) {
        Optional<Resenia> reseniaOpt = reseniaRepository.findById(id);
        if (reseniaOpt.isEmpty()) {
            return "reseña no encontrada";
        }else if (!usuarioRepository.existsByEmail(email)) {
            return "usuario con este email no existe";
        } 
        Usuario usuario = usuarioRepository.findByEmail(email);
        Resenia resenia = reseniaOpt.get();    
        usuario.setResenia(resenia);
        resenia.setUsuario(usuario);

        usuarioRepository.save(usuario);
        reseniaRepository.save(resenia);
        return "Reseña guardad al usuario con exito!";
    }

    public String asignarInscripcion(String email, Integer id) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        //optional es una clase que puede contener un valor o no
        Optional<Inscripcion> inscripcion = inscripcionRepository.findById(id);
        if (!usuarioRepository.existsByEmail(email)) {
            return "usuario no encontrado";
        } else if (inscripcion.isEmpty()) {
            return "notificacion no encontrada";
        }
        usuario.getInscripcion().add(inscripcion.get());
        inscripcion.get().setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "Inscripcion asignada correctamente al usuario";
    }

}
