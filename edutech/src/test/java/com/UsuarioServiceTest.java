package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void almacenarUsuarioRepetido() {
        Usuario usuario = new Usuario();
        usuario.setEmail("1");
        usuario.setContrasena("1");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

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
}
