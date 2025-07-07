//creado por matías borquez

package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Rol;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.RolRepository;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.RolService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void almacenarExistente() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("matias");

        when(rolRepository.existsById(rol.getId())).thenReturn(true);

        String resultado = rolService.almacenar(rol);

        assertEquals("El rol ya existe", resultado);
    }

    @Test
    void almacenarNuevo() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("matias");

        when(rolRepository.existsById(rol.getId())).thenReturn(false);

        String resultado = rolService.almacenar(rol);

        assertEquals("Rol almacenado con éxito", resultado);
    }

    @Test
    void listar() {
        Rol rol1 = new Rol();
        rol1.setId(1);
        rol1.setNombre("Profesor");

        Rol rol2 = new Rol();
        rol2.setId(2);
        rol2.setNombre("Administrador");

        when(rolRepository.findAll()).thenReturn(List.of(rol1, rol2));

        List<Rol> roles = rolService.listar();

        assertEquals(2, roles.size());
        assertEquals("Profesor", roles.get(0).getNombre());
        assertEquals("Administrador", roles.get(1).getNombre());
    }

    @Test
    void modificarRolExistente() {
        Rol original = new Rol();
        original.setId(1);
        original.setNombre("Antiguo");

        Rol actualizado = new Rol();
        actualizado.setNombre("Nuevo");

        when(rolRepository.findById(1)).thenReturn(
            java.util.Optional.of(original)
        );

        String resultado = rolService.modificar(1, actualizado);

        assertEquals("Rol actualizado con éxito", resultado);
    }

    @Test
    void modificarRolNoExistente() {
        Rol actualizado = new Rol();
        actualizado.setNombre("Nuevo");

        when(rolRepository.findById(1)).thenReturn(java.util.Optional.empty());

        String resultado = rolService.modificar(1, actualizado);

        assertEquals("Rol no encontrado", resultado);
    }

    @Test
    void eliminarRolExistente() {
        Rol rol = new Rol();
        rol.setId(1);

        when(rolRepository.findById(1)).thenReturn(java.util.Optional.of(rol));

        var resultado = rolService.eliminar(1);

        assertEquals(Boolean.TRUE, resultado.get("rol eliminado"));
    }

    @Test
    void eliminarRolNoExistente() {
        when(rolRepository.findById(1)).thenReturn(java.util.Optional.empty());

        var resultado = rolService.eliminar(1);

        assertEquals(Boolean.FALSE, resultado.get("rol no encontrado"));
    }

    @Test
    void asignarRolAUsuario_rolNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(false);

        String resultado = rolService.asignarRolAUsuario(1, "correo@wea.cl");

        assertEquals("rol no existe", resultado);
    }

    @Test
    void asignarRolAUsuario_usuarioNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.existsByEmail("correo@wea.cl")).thenReturn(
            false
        );

        String resultado = rolService.asignarRolAUsuario(1, "correo@wea.cl");

        assertEquals("usuario no existe!", resultado);
    }

    @Test
    void asignarRolAUsuario_ok() {
        Rol rol = new Rol();
        rol.setId(1);

        Usuario usuario = new Usuario();

        when(rolRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.existsByEmail("correo@gmail.cl")).thenReturn(
            true
        );
        when(rolRepository.findById(1)).thenReturn(java.util.Optional.of(rol));
        when(usuarioRepository.findByEmail("correo@gmail.cl")).thenReturn(
            usuario
        );

        String resultado = rolService.asignarRolAUsuario(1, "correo@gmail.cl");

        assertEquals("Rol asignado al usuario con éxito", resultado);
    }

    @Test
    void asignarRolAProfesor_rolNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(false);

        String resultado = rolService.asignarRolAProfesor(1, "12345678-9");

        assertEquals("rol no existe", resultado);
    }

    @Test
    void asignarRolAProfesor_profesorNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(true);
        when(profesorRepository.existsByRut("12345678-9")).thenReturn(false);

        String resultado = rolService.asignarRolAProfesor(1, "12345678-9");

        assertEquals("profesor no existe!", resultado);
    }

    @Test
    void asignarRolAProfesor_ok() {
        Rol rol = new Rol();
        rol.setId(1);

        Profesor profe = new Profesor();

        when(rolRepository.existsById(1)).thenReturn(true);
        when(profesorRepository.existsByRut("12345678-9")).thenReturn(true);
        when(rolRepository.findById(1)).thenReturn(java.util.Optional.of(rol));
        when(profesorRepository.findByRut("12345678-9")).thenReturn(profe);

        String resultado = rolService.asignarRolAProfesor(1, "12345678-9");

        assertEquals("Rol asignado al profesor", resultado);
    }

    @Test
    void asignarRolAAdministrador_rolNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(false);

        String resultado = rolService.asignarRolAAdministrador(1, "12345678-9");

        assertEquals("rol no existe", resultado);
    }

    @Test
    void asignarRolAAdministrador_adminNoExiste() {
        when(rolRepository.existsById(1)).thenReturn(true);
        when(administradorRepository.existsByRut("12345678-9")).thenReturn(
            false
        );

        String resultado = rolService.asignarRolAAdministrador(1, "12345678-9");

        assertEquals("administrador no existe!", resultado);
    }

    @Test
    void asignarRolAAdministrador_ok() {
        Rol rol = new Rol();
        rol.setId(1);

        Administrador admin = new Administrador();

        when(rolRepository.existsById(1)).thenReturn(true);
        when(administradorRepository.existsByRut("12345678-9")).thenReturn(
            true
        );
        when(rolRepository.findById(1)).thenReturn(java.util.Optional.of(rol));
        when(administradorRepository.findByRut("12345678-9")).thenReturn(admin);

        String resultado = rolService.asignarRolAAdministrador(1, "12345678-9");

        assertEquals("Rol asignado al administrador", resultado);
    }
}
