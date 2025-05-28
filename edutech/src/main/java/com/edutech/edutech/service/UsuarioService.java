
//Creado por Matías Borquez
package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.UsuarioModificarDto;

import com.edutech.edutech.dto.AsignarUsuarioDto;
import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.model.Persona;
import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.model.Tarjeta;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.InscripcionRepository;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.repository.ReseniaRepository;
import com.edutech.edutech.repository.TarjetaRepository;
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

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public String almacenar(Usuario usuario) {
        Usuario validacion = usuarioRepository.findByEmail(usuario.getEmail());
        if (validacion != null) {
            return "error: usuario con este email ya existe";
        } else {
            usuarioRepository.save(usuario);
            return "usuario " + usuario.getEmail() + " almacenada con exito";
        }
    }

    // listar
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // buscar por email
    public List<Usuario> buscar(String email) {
        return usuarioRepository.findByEmailContaining(email);
    }

    // modificar
    public String modificarUsuario(String email, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setPersona(usuarioActualizado.getPersona());

            usuarioRepository.save(usuario);
            return "usuario actualizado con exito!";
        } else {
            return "email del usuario no existe!";
        }

    }

    // eliminar
    public Map<String, Boolean> eliminarUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        // map es una estructura de datos que almacena pares clave-valor
        // para devolver un string y boolean
        Map<String, Boolean> respuesta = new HashMap<>();
        if (usuario != null) {
            // Elimina la relación en ambos lados con cursos
            for (Curso curso : usuario.getCursos()) {
                curso.getUsuarios().remove(usuario);
                cursoRepository.save(curso);
            }
            usuario.getCursos().clear();

            // eliminar relacion con tarjeta
            if (usuario.getTarjeta() != null) {
                for (Tarjeta tarjeta : usuario.getTarjeta()) {
                    tarjeta.setUsuario(null);
                    tarjetaRepository.save(tarjeta);
                }
            }
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

    public String asignarAsistencia(String email, int id) {
        if (!usuarioRepository.existsByEmail(email)) {
            return "el email ingresado no existe";
        } else if (!asistenciaRepository.existsById(id)) {
            return "La especialidad no existe";
        } else {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Asistencia asistencia = asistenciaRepository.findById(id);
            usuario.getAsistencia().add(asistencia);
            asistencia.setUsuario(usuario); // Establece la relación inversa
            usuarioRepository.save(usuario);
            return "asistencia asignada correctamente al usuario";
        }

    }

    public String asignarNotificacion(String email, int id) {
        if (!usuarioRepository.existsByEmail(email)) {
            return "el email no existe..";
        } else if (!notificacionRepository.existsById(id)) {
            return "notificacion no existe";
        }
        Usuario usuario = usuarioRepository.findByEmail(email);
        Notificacion notificacion = notificacionRepository.findById(id);
        usuario.getNotificacion().add(notificacion);
        notificacion.setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "notificacion asignada correctamente al usuario";

    }

    public String almacenarResenia(String email, int id) {
        if (!reseniaRepository.existsById(id)) {
            return "reseña no encontrada";
        } else if (!usuarioRepository.existsByEmail(email)) {
            return "usuario con este email no existe";
        }
        Resenia resenia = reseniaRepository.findById(id).orElse(null);
        Usuario usuario = usuarioRepository.findByEmail(email);

        usuario.setResenia(resenia);
        resenia.setUsuario(usuario);

        usuarioRepository.save(usuario);
        reseniaRepository.save(resenia);
        return "Reseña guardad al usuario con exito!";
    }

    public String asignarInscripcion(String email, int id) {
        if (!usuarioRepository.existsByEmail(email)) {
            return "usuario no encontrado";
        } else if (inscripcionRepository.existsById(id)) {
            return "notificacion no encontrada";
        }
        Usuario usuario = usuarioRepository.findByEmail(email);
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElse(null);

        inscripcion.setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "Inscripcion asignada correctamente al usuario";
    }

    // -------------------- DTO --------------------

    public String persona(AsignarUsuarioDto dto) {
        if (!personaRepository.existsByRut(dto.getRut())) {
            return "la persona con este rut no existe";
        } else if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "usuario con este email no existe";
        }

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        Persona persona = personaRepository.findByRut(dto.getRut());

        usuario.setPersona(persona);
        persona.setUsuario(usuario);
        usuarioRepository.save(usuario);
        personaRepository.save(persona);
        return "persona y usuarios asociados con exito!";

    }

    public String asistencia(AsignarUsuarioDto dto) {
        if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "el email ingresado no existe";
        } else if (!asistenciaRepository.existsById(dto.getId())) {
            return "La especialidad no existe";
        }
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        Asistencia asistencia = asistenciaRepository.findById(dto.getId());
        usuario.getAsistencia().add(asistencia);
        asistencia.setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "asistencia asignada correctamente al usuario";
    }

    public String resenia(AsignarUsuarioDto dto) {
        if (!reseniaRepository.existsById(dto.getId())) {
            return "reseña no encontrada";
        } else if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "usuario con este email no existe";
        }
        Resenia resenia = reseniaRepository.findById(dto.getId()).orElse(null);
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

        usuario.setResenia(resenia);
        resenia.setUsuario(usuario);

        usuarioRepository.save(usuario);
        reseniaRepository.save(resenia);
        return "Reseña guardad al usuario con exito!";
    }

    public String notificacion(AsignarUsuarioDto dto) {
        if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "el email no existe..";
        } else if (!notificacionRepository.existsById(dto.getId())) {
            return "notificacion no existe";
        }
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        Notificacion notificacion = notificacionRepository.findById(dto.getId());
        notificacion.setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "notificacion asignada correctamente al usuario";

    }

    public String inscripcion(AsignarUsuarioDto dto) {
        if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "usuario no encontrado";
        } else if (!inscripcionRepository.existsById(dto.getId())) {
            return "notificacion no encontrada";
        }
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        Inscripcion inscripcion = inscripcionRepository.findById(dto.getId()).orElse(null);

        inscripcion.setUsuario(usuario); // Establece la relación inversa
        usuarioRepository.save(usuario);
        return "Inscripcion asignada correctamente al usuario";
    }

    public String modificar(UsuarioModificarDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario != null) {
            usuario.setEmail(dto.getEmail());
            usuario.setContrasena(dto.getContrasena());
            usuarioRepository.save(usuario);
            return "usuario actualizado con exito!";
        } else {
            return "email del usuario no existe!";
        }

    }

    public List<UsuarioEmailDto> obtUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getEmail()))
                .collect(Collectors.toList());
    }

}
