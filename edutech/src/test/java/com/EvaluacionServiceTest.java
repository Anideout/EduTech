package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import com.edutech.edutech.service.EvaluacionService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    @Test
    void almacenar() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);
        evaluacion.setNombre("Prueba");

        String resultado = evaluacionService.almacenar(evaluacion);

        assertEquals("evaluacion  almacenada con exito!", resultado);
    }

    @Test
    void listar() {
        Evaluacion e1 = new Evaluacion();
        e1.setId(1);
        e1.setNombre("Prueba 1");
        Evaluacion e2 = new Evaluacion();
        e2.setId(2);
        e2.setNombre("Prueba 2");

        when(evaluacionRepository.findAll()).thenReturn(List.of(e1, e2));

        var resultado = evaluacionService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Prueba 1", resultado.get(0).getNombre());
        assertEquals("Prueba 2", resultado.get(1).getNombre());
    }

    @Test
    void buscar() {
        Evaluacion e1 = new Evaluacion();
        e1.setId(1);
        e1.setNombre("Prueba Parcial");

        when(evaluacionRepository.findByNombreContaining("Parcial")).thenReturn(
            List.of(e1)
        );

        var resultado = evaluacionService.buscar("Parcial");

        assertEquals(1, resultado.size());
        assertEquals("Prueba Parcial", resultado.get(0).getNombre());
    }

    @Test
    void modificarEvaluacionExistente() {
        Evaluacion original = new Evaluacion();
        original.setId(1);
        original.setNombre("Antigua");

        Evaluacion mod = new Evaluacion();
        mod.setNombre("Nueva");

        when(evaluacionRepository.findById(1)).thenReturn(
            Optional.of(original)
        );

        String resultado = evaluacionService.modificar(1, mod);

        assertEquals("evaluacion guardada con éxito", resultado);
        assertEquals("Nueva", original.getNombre());
    }

    @Test
    void modificarEvaluacionNoExistente() {
        Evaluacion mod = new Evaluacion();
        mod.setNombre("Nueva");

        when(evaluacionRepository.findById(1)).thenReturn(Optional.empty());

        String resultado = evaluacionService.modificar(1, mod);

        assertEquals("evaluacion no encontrada", resultado);
    }

    @Test
    void eliminarEvaluacionExistente() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);

        when(evaluacionRepository.findById(1)).thenReturn(
            Optional.of(evaluacion)
        );

        var resultado = evaluacionService.eliminar(1);

        assertEquals(Boolean.TRUE, resultado.get("evaluacion eliminada"));
    }

    @Test
    void eliminarEvaluacionNoExistente() {
        when(evaluacionRepository.findById(1)).thenReturn(Optional.empty());

        var resultado = evaluacionService.eliminar(1);

        assertEquals(Boolean.FALSE, resultado.get("evaluacion no encontrada"));
    }
}
