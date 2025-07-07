//creado por matías borquez
package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Persona;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.repository.InscripcionRepository;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.repository.ReseniaRepository;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.UsuarioService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private NotificacionRepository notificacionRepository;

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @Mock
    private ReseniaRepository reseniaRepository;

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void almacenarUsuarioRepetido() {
        Usuario usuario = new Usuario();
        usuario.setEmail("1");
        usuario.setContrasena("1");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(
            true
        );

        String resultado = usuarioService.almacenar(usuario);

        assertEquals("error: usuario ya existe!", resultado);
    }

    @Test
    void almacenarUsuarioNuevo() {
        Usuario usuario = new Usuario();
        usuario.setEmail("1");
        usuario.setContrasena("1");

        String resultado = usuarioService.almacenar(usuario);

        assertEquals("Usuario almacenada con exito!", resultado);
    }

    @Test
    void listar() {
        Usuario u1 = new Usuario();
        u1.setEmail("1");
        u1.setContrasena("1");

        Usuario u2 = new Usuario();
        u2.setEmail("2");
        u2.setContrasena("2");

        when(usuarioRepository.findAll()).thenReturn(List.of(u1, u2));

        List<Usuario> usuarios = usuarioService.listar();

        assertEquals(2, usuarios.size());
        assertEquals("1", usuarios.get(0).getEmail());
        assertEquals("2", usuarios.get(1).getEmail());
    }

    @Test
    void asignarnada() {
        Usuario usuario = new Usuario();
        usuario.setEmail("1");

        Persona persona = new Persona();
        persona.setRut("111");

        String resultado = usuarioService.almacenarPersona(
            usuario.getEmail(),
            persona.getRut()
        );

        assertEquals("la persona con este rut no existe", resultado);
    }

    @Test
    void asignarPersona() {
        Usuario usuario = new Usuario();
        usuario.setEmail("1");

        Persona persona = new Persona();
        persona.setRut("111");

        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(
            usuario
        );
        when(personaRepository.findByRut(persona.getRut())).thenReturn(persona);

        String resultado = usuarioService.almacenarPersona(
            usuario.getEmail(),
            persona.getRut()
        );

        assertEquals("persona y usuarios asociados con exito!", resultado);
    }

    @Test
    void asignarAsistenciaUsuarioNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        String resultado = usuarioService.asignarAsistencia(email, id);

        assertEquals("el email ingresado no existe", resultado);
    }

    @Test
    void asignarAsistenciaEspecialidadNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(true);
        when(asistenciaRepository.existsById(id)).thenReturn(false);

        String resultado = usuarioService.asignarAsistencia(email, id);

        assertEquals("La especialidad no existe", resultado);
    }

    @Test
    void asignarNotificacionUsuarioNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        String resultado = usuarioService.asignarNotificacion(email, id);

        assertEquals("el email no existe..", resultado);
    }

    @Test
    void asignarNotificacionNotificacionNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(true);
        when(notificacionRepository.existsById(id)).thenReturn(false);

        String resultado = usuarioService.asignarNotificacion(email, id);

        assertEquals("notificacion no existe", resultado);
    }

    @Test
    void asignarReseniaReseniaNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(reseniaRepository.existsById(id)).thenReturn(false);

        String resultado = usuarioService.almacenarResenia(email, id);

        assertEquals("reseña no encontrada", resultado);
    }

    @Test
    void asignarReseniaUsuarioNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(reseniaRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        String resultado = usuarioService.almacenarResenia(email, id);

        assertEquals("usuario con este email no existe", resultado);
    }

    @Test
    void asignarInscripcionUsuarioNoExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        String resultado = usuarioService.asignarInscripcion(email, id);

        assertEquals("usuario no encontrado", resultado);
    }

    @Test
    void asignarInscripcionExiste() {
        String email = "test@email.com";
        int id = 1;

        when(usuarioRepository.existsByEmail(email)).thenReturn(true);
        when(inscripcionRepository.existsById(id)).thenReturn(false);

        String resultado = usuarioService.asignarInscripcion(email, id);

        assertEquals("inscripcion no encontrada", resultado);
    }
}
