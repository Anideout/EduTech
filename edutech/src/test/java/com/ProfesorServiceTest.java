package com;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import com.edutech.edutech.model.Profesor;

import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.service.ProfesorService;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void almacenarProfesorRepetido() {
        Profesor profesor = new Profesor();
        profesor.setRut("11.111.111-1");
        profesor.setNombre("Profesor 1");

        when(profesorRepository.existsById(profesor.getRut())).thenReturn(true);

        String resultado = profesorService.almacenar(profesor);

        assertEquals("NOK", resultado);
    }

    @Test
    void almacenarProfesorNuevo() {
        Profesor profesor = new Profesor();
        profesor.setRut("11.111.111-1");
        profesor.setNombre("Profesor 1");

        String resultado = profesorService.almacenar(profesor);

        assertEquals("OK", resultado);
    }

    @Test
    void listar() {
        Profesor p1 = new Profesor();
        p1.setRut("11.111.111-1");
        p1.setNombre("Profesor 1");

        Profesor p2 = new Profesor();
        p2.setRut("22.222.222-2");
        p2.setNombre("Profesor 2");

        when(profesorRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Profesor> resultado = profesorService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Profesor 1", resultado.get(0).getNombre());
        assertEquals("Profesor 2", resultado.get(1).getNombre());
    }

    @Test
    void eliminarProfesor() {
        String rut = "11.111.111-1";
        Profesor profesor = new Profesor();
        profesor.setRut(rut);
        profesor.setNombre("Profesor 1");
        when(profesorRepository.findByRut(rut)).thenReturn(profesor);

        Map<String, Boolean> resultado = profesorService.eliminarProfesor(rut);

        assertEquals(Boolean.TRUE, resultado.get("profesor eliminado"));
    }

    @Test
    void eliminarProfesorNoExistente() {
        String rut = "11.111.111-1";
        when(profesorRepository.findByRut(rut)).thenReturn(null);

        Map<String, Boolean> resultado = profesorService.eliminarProfesor(rut);

        assertEquals(Boolean.FALSE, resultado.get("profesor no encontrado"));
    }

    @Test
    void modificarProfesor() {
        String rut = "11.111.111-1";
        Profesor profesorExistente = new Profesor();
        profesorExistente.setRut(rut);
        profesorExistente.setNombre("Nombre Original");
        profesorExistente.setApellido("Apellido Original");
        profesorExistente.setDireccion("Dirección Original");
        profesorExistente.setContrasena("Contraseña Original");

        Profesor profesorActualizado = new Profesor();
        profesorActualizado.setNombre("Nuevo Nombre");
        profesorActualizado.setApellido("Nuevo Apellido");
        profesorActualizado.setDireccion("Nueva Dirección");
        profesorActualizado.setContrasena("Nueva Contraseña");

        when(profesorRepository.findByRut(rut)).thenReturn(profesorExistente);

        String resultado = profesorService.actualizarProfesor(rut, profesorActualizado);

        assertEquals("profesor actualizado con exito!", resultado);
        assertEquals("Nuevo Nombre", profesorExistente.getNombre());
        assertEquals("Nuevo Apellido", profesorExistente.getApellido());
        assertEquals("Nueva Dirección", profesorExistente.getDireccion());
        assertEquals("Nueva Contraseña", profesorExistente.getContrasena());
    }

    @Test
    void modificarProfesorNoExistente() {
        String rut = "11.111.111-1";
        when(profesorRepository.findByRut(rut)).thenReturn(null);

        Profesor profesorActualizado = new Profesor();
        profesorActualizado.setRut(rut);
        profesorActualizado.setNombre("Nombre Modificado");
        profesorActualizado.setApellido("Apellido Modificado");
        profesorActualizado.setDireccion("Direccion Modificado");
        profesorActualizado.setContrasena("Contrasena Modificada");

        String resultado = profesorService.actualizarProfesor(rut, profesorActualizado);

        assertEquals("rut del profesor no existe!", resultado);
    }
}
