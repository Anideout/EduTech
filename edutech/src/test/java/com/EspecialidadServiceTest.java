//creado por sergio puebla 
package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Especialidad;
import com.edutech.edutech.repository.EspecialidadRepository;
import com.edutech.edutech.service.EspecialidadService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        when(especialidadRepository.existsById(especialidad.getId())).thenReturn(true);

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
}
