//creado por matías borquez

package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Sede;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.SedeRepository;
import com.edutech.edutech.service.SedeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SedeServiceTest {

    @Mock
    private SedeRepository sedeRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private SedeService sedeService;

    @Test
    void almacenarSedeRepetida() {
        Sede sede = new Sede();
        sede.setId(1);
        sede.setNombre("Sede 1");

        when(sedeRepository.existsById(sede.getId())).thenReturn(true);

        String resultado = sedeService.almacenar(sede);

        assertEquals("La sede ya existe", resultado);
    }

    @Test
    void almacenarSedeNueva() {
        Sede sede = new Sede();
        sede.setId(1);
        sede.setNombre("Sede 1");

        String resultado = sedeService.almacenar(sede);

        assertEquals("Sede almacenada con exito", resultado);
    }

    @Test
    void listar() {
        Sede s1 = new Sede();
        s1.setId(1);
        s1.setNombre("sede 1");

        Sede s2 = new Sede();
        s2.setId(2);
        s2.setNombre("sede 2");

        when(sedeRepository.findAll()).thenReturn(List.of(s1, s2));

        List<Sede> resultado = sedeService.listar();

        assertEquals(2, resultado.size());
        assertEquals("sede 1", resultado.get(0).getNombre());
        assertEquals("sede 2", resultado.get(1).getNombre());
    }

    @Test
    void modificarSedeExistente() {
        Sede sedeActualizada = new Sede();
        sedeActualizada.setNombre("Nueva Sede");

        when(sedeRepository.existsById(1)).thenReturn(true);

        String resultado = sedeService.modificar(1, sedeActualizada);

        assertEquals("Sede modificada con exito", resultado);
    }

    @Test
    void eliminarSedeExistente() {
        int id = 1;

        // Crear una sede mock sin profes ni admin
        Sede sede = new Sede();
        sede.setId(id);
        sede.setProfesores(new ArrayList<>()); // Lista vacía
        sede.setAdministrador(null); // Sin admin

        // Mock del repository
        when(sedeRepository.findById(id)).thenReturn(Optional.of(sede));

        Map<String, Boolean> resultado = sedeService.eliminar(id);

        // El key correcto según tu método es "Sede eliminada"
        assertEquals(Boolean.TRUE, resultado.get("Sede eliminada"));
    }

    @Test
    void eliminarSedeNoExistente() {
        int id = 1;

        // Mock para que no encuentre la sede
        when(sedeRepository.findById(id)).thenReturn(Optional.empty());

        Map<String, Boolean> resultado = sedeService.eliminar(id);

        // El key correcto según tu método es "Sede no existe"
        assertEquals(Boolean.FALSE, resultado.get("Sede no existe"));
    }

    @Test
    void eliminarSedeConProfesores() {
        int id = 1;

        Sede sede = new Sede();
        sede.setId(id);
        sede.setProfesores(List.of(new Profesor())); // Con profes
        sede.setAdministrador(null);

        when(sedeRepository.findById(id)).thenReturn(Optional.of(sede));

        Map<String, Boolean> resultado = sedeService.eliminar(id);

        assertEquals(
            Boolean.FALSE,
            resultado.get("Sede tiene profes asignados")
        );
    }

    @Test
    void eliminarSedeConAdmin() {
        int id = 1;

        Sede sede = new Sede();
        sede.setId(id);
        sede.setProfesores(new ArrayList<>());
        sede.setAdministrador(new Administrador()); // Con admin

        when(sedeRepository.findById(id)).thenReturn(Optional.of(sede));

        Map<String, Boolean> resultado = sedeService.eliminar(id);

        assertEquals(Boolean.FALSE, resultado.get("Sede tiene admin asignado"));
    }

    @Test
    void asignarProfesorSede_sedeNoExiste() {
        when(sedeRepository.existsById(1)).thenReturn(false);

        String resultado = sedeService.asignarProfesorSede(1, "12345678-9");

        assertEquals("La sede ingresada no existe", resultado);
    }

    @Test
    void asignarProfesorSede_profesorNoExiste() {
        when(sedeRepository.existsById(1)).thenReturn(true);
        when(profesorRepository.existsByRut("12345678-9")).thenReturn(false);

        String resultado = sedeService.asignarProfesorSede(1, "12345678-9");

        assertEquals("el profesor ingresado no existe", resultado);
    }

    @Test
    void asignarProfesorSede_ok() {
        Sede sede = new Sede();
        sede.setId(1);
        sede.setProfesores(new java.util.ArrayList<>());

        Profesor profe = new Profesor();
        profe.setRut("12345678-9");
        profe.setSedes(new java.util.ArrayList<>());

        when(sedeRepository.existsById(1)).thenReturn(true);
        when(profesorRepository.existsByRut("12345678-9")).thenReturn(true);
        when(sedeRepository.findById(1)).thenReturn(
            java.util.Optional.of(sede)
        );
        when(profesorRepository.findByRut("12345678-9")).thenReturn(profe);

        String resultado = sedeService.asignarProfesorSede(1, "12345678-9");

        assertEquals("Profesor asignado correctamente al curso", resultado);
        assertEquals(1, sede.getProfesores().size());
        assertEquals(1, profe.getSedes().size());
    }

    @Test
    void asignarSedeAdministrador_sedeNoExiste() {
        when(sedeRepository.existsById(1)).thenReturn(false);

        String resultado = sedeService.asignarSedeAdministrador(
            1,
            "12345678-9"
        );

        assertEquals("La sede ingresada no existe", resultado);
    }

    @Test
    void asignarSedeAdministrador_adminNoExiste() {
        when(sedeRepository.existsById(1)).thenReturn(true);
        when(administradorRepository.existsByRut("12345678-9")).thenReturn(
            false
        );

        String resultado = sedeService.asignarSedeAdministrador(
            1,
            "12345678-9"
        );

        assertEquals("El administrador ingresado no existe", resultado);
    }

    @Test
    void asignarSedeAdministrador_ok() {
        Sede sede = new Sede();
        sede.setId(1);

        Administrador admin = new Administrador();
        admin.setRut("12345678-9");

        when(sedeRepository.existsById(1)).thenReturn(true);
        when(administradorRepository.existsByRut("12345678-9")).thenReturn(
            true
        );
        when(sedeRepository.findById(1)).thenReturn(
            java.util.Optional.of(sede)
        );
        when(administradorRepository.findByRut("12345678-9")).thenReturn(admin);

        String resultado = sedeService.asignarSedeAdministrador(
            1,
            "12345678-9"
        );

        assertEquals(
            "Administrador asignado correctamente a la sede",
            resultado
        );
        assertEquals(admin, sede.getAdministrador());
        assertEquals(sede, admin.getSede());
    }
}
