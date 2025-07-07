//creado por sergio puebla 

package com;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Rol;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.RolRepository;
import com.edutech.edutech.service.AdministradorService;

@ExtendWith(MockitoExtension.class)
public class AdministradorServiceTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private AdministradorService administradorService;

    @Test
    void almacenarAdministradorRepetido() {
        Administrador administrador = new Administrador();
        administrador.setRut("11.111.111-1");
        administrador.setNombre("Administrador 1");

        when(administradorRepository.existsByRut(administrador.getRut())).thenReturn(true);

        String resultado = administradorService.almacenar(administrador);

        assertEquals("El administrador ya existe", resultado);
    }

    @Test
    void almacenarAdministradorNuevo() {
        Administrador administrador = new Administrador();
        administrador.setRut("11.111.111-1");
        administrador.setNombre("Administrador 1");

        String resultado = administradorService.almacenar(administrador);

        assertEquals("Administrador almacenado con éxito", resultado);
    }

    @Test
    void listar() {
        Administrador a1 = new Administrador();
        a1.setRut("11.111.111-1");
        a1.setNombre("admin 1");

        Administrador a2 = new Administrador();
        a2.setRut("22.222.222-2");
        a2.setNombre("admin 2");

        when(administradorRepository.findAll()).thenReturn(List.of(a1, a2));

        List<Administrador> resultado = administradorService.listar();

        assertEquals(2, resultado.size());
        assertEquals("admin 1", resultado.get(0).getNombre());
        assertEquals("admin 2", resultado.get(1).getNombre());
    }

    @Test
    void eliminarAdmin() {
        String rut = "11.111.111-1";
        Administrador admin = new Administrador();
        admin.setRut(rut);
        admin.setNombre("admin 1");
        when(administradorRepository.findByRut(rut)).thenReturn(admin);

        Map<String, Boolean> resultado = administradorService.eliminar(rut);

        assertEquals(Boolean.TRUE, resultado.get("administrador eliminado"));
    }

    @Test
    void eliminarAdminNoExistente() {
        String rut = "11.111.111-1";
        when(administradorRepository.findByRut(rut)).thenReturn(null);

        Map<String, Boolean> resultado = administradorService.eliminar(rut);

        assertEquals(Boolean.FALSE, resultado.get("administrador no encontrado"));
    }

    @Test
    void modificarAdministradorExistente() {
        String rut = "11.111.111-1";
        Administrador adminExistente = new Administrador();
        adminExistente.setRut(rut);
        adminExistente.setNombre("admin 1");

        Administrador adminActualizado = new Administrador();
        adminActualizado.setNombre("admin actualizado");
        adminActualizado.setApellido("apellido actualizado");
        adminActualizado.setContrasena("nuevaContrasena");

        when(administradorRepository.findByRut(rut)).thenReturn(adminExistente);

        String resultado = administradorService.modificar(rut, adminActualizado);

        assertEquals("Administrador actualizado con éxito", resultado);
        assertEquals("admin actualizado", adminExistente.getNombre());
        assertEquals("apellido actualizado", adminExistente.getApellido());
        assertEquals("nuevaContrasena", adminExistente.getContrasena());
    }

    @Test
    void modificarAdministradorNoExistente() {
        String rut = "11.111.111-1";
        Administrador adminActualizado = new Administrador();
        adminActualizado.setNombre("admin actualizado");

        when(administradorRepository.findByRut(rut)).thenReturn(null);

        String resultado = administradorService.modificar(rut, adminActualizado);

        assertEquals("Administrador no encontrado", resultado);
    }

    @Test
    void asignarRolExitoso() {
        String rut = "12345678-9";
        int id = 1;

        Administrador admin = new Administrador();
        admin.setRut(rut);

        Rol rol = new Rol();
        rol.setId(id);

        when(administradorRepository.findByRut(rut)).thenReturn(admin);
        when(rolRepository.findById(id)).thenReturn(Optional.of(rol));

        String resultado = administradorService.asignarRol(rut, id);

        assertEquals("Rol asignado al administrador", resultado);
        assertEquals(rol, admin.getRol());
    }

    @Test
    void asignarRolNoExitosoAdministradorNoEncontrado() {
        String rut = "12345678-9";
        int id = 1;

        when(administradorRepository.findByRut(rut)).thenReturn(null);
        when(rolRepository.findById(id)).thenReturn(Optional.of(new Rol()));

        String resultado = administradorService.asignarRol(rut, id);

        assertEquals("Administrador o rol no encontrado", resultado);
    }

    @Test
    void asignarRolNoExitosoRolNoEncontrado() {
        String rut = "12345678-9";
        int id = 1;

        Administrador admin = new Administrador();
        admin.setRut(rut);

        when(administradorRepository.findByRut(rut)).thenReturn(admin);
        when(rolRepository.findById(id)).thenReturn(Optional.empty());

        String resultado = administradorService.asignarRol(rut, id);

        assertEquals("Administrador o rol no encontrado", resultado);
    }
}
