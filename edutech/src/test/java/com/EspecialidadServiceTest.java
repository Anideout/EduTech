//creado por sergio puebla
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

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.repository.EspecialidadRepository;
import com.edutech.edutech.service.EspecialidadService;

@ExtendWith(MockitoExtension.class)
public class EspecialidadServiceTest {

    @Mock
    private EspecialidadRepository especialidadRepository;

    @InjectMocks
    private EspecialidadService especialidadService;

    @Test
    void almacenarEspecialidadRepetida() {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(1);
        especialidad.setNombre("progra");

        when(
                especialidadRepository.existsById(especialidad.getId())).thenReturn(true);

        String resultado = especialidadService.almacenar(especialidad);

        assertEquals("error: especialidad ya existe!", resultado);
    }

    @Test
    void almacenarEspecialidadNueva() {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(1);
        especialidad.setNombre("progra");

        String resultado = especialidadService.almacenar(especialidad);

        assertEquals("Especialidad almacenada con exito!", resultado);
    }

    @Test
    void listar() {
        Especialidad espe = new Especialidad();
        espe.setId(1);
        espe.setNombre("progra");

        Especialidad espe2 = new Especialidad();
        espe2.setId(2);
        espe2.setNombre("bd");

        when(especialidadRepository.findAll()).thenReturn(List.of(espe, espe2));

        List<Especialidad> resultado = especialidadService.listar();

        assertEquals(2, resultado.size());
        assertEquals("progra", resultado.get(0).getNombre());
        assertEquals("bd", resultado.get(1).getNombre());
    }

    @Test
    void eliminarEspecialidad() {
        int id = 1;
        Especialidad espe = new Especialidad();
        espe.setId(id);
        espe.setNombre("especialidad");

        when(especialidadRepository.findById(id)).thenReturn(espe);

        Map<String, Boolean> resultado = especialidadService.eliminar(id);

        assertEquals(
                Boolean.TRUE,
                resultado.get("especialidad eliminada con exito!"));
    }

    @Test
    void eliminarNada() {
        int id = 1;
        when(especialidadRepository.findById(id)).thenReturn(null);

        Map<String, Boolean> resultado = especialidadService.eliminar(id);

        assertEquals(
                Boolean.FALSE,
                resultado.get("especialiadad no encontrada..."));
    }

    @Test
    void modificar() {
        int id = 1;
        Especialidad espe = new Especialidad();
        espe.setId(id);
        espe.setNombre("matias");

        Especialidad especiaAc = new Especialidad();
        especiaAc.setNombre("nombre");

        when(especialidadRepository.findById(id)).thenReturn(especiaAc);

        String resultado = especialidadService.modificar(id, especiaAc);

        assertEquals("especialidad modificada con exito", resultado);
        assertEquals("nombre", especiaAc.getNombre());
    }

    @Test
    void modificarNadie() {
        int id = 1;
        Especialidad espe = new Especialidad();
        espe.setId(id);
        espe.setNombre("matias");
        when(especialidadRepository.findById(id)).thenReturn(null);

        String resultado = especialidadService.modificar(id, espe);

        assertEquals("especialidad no encontrada", resultado);
    }
}
