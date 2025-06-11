package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Rol;
import com.edutech.edutech.repository.RolRepository;
import com.edutech.edutech.service.RolService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

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

}
