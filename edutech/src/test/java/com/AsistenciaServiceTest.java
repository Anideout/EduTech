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

import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.service.Asistenciaservice;

@ExtendWith(MockitoExtension.class)
public class AsistenciaServiceTest {
    @Mock
    private AsistenciaRepository asistenciaRepository;

    @InjectMocks
    private Asistenciaservice asistenciaService;

    @Test
    void almacenarAsistenciaRepetida() {
        Asistencia asistencia = new Asistencia();
        asistencia.setId(1);
        asistencia.setFecha("2023-10-01");

        when(asistenciaRepository.existsById(asistencia.getId())).thenReturn(true);

        String resultado = asistenciaService.almacenar(asistencia);

        assertEquals("error: asistencia ya existe!", resultado);
    }

    @Test
    void almacenarAsistenciaNueva() {
        Asistencia asistencia = new Asistencia();
        asistencia.setId(1);
        asistencia.setFecha("2023-10-01");

        String resultado = asistenciaService.almacenar(asistencia);

        assertEquals("Asistencia guardada correctamente", resultado);
    }

    @Test
    void listar() {
        Asistencia asistencia1 = new Asistencia();
        asistencia1.setId(1);
        asistencia1.setFecha("2023-10-01");

        Asistencia asistencia2 = new Asistencia();
        asistencia2.setId(2);
        asistencia2.setFecha("2023-10-02");

        when(asistenciaRepository.findAll()).thenReturn(List.of(asistencia1, asistencia2));

        List<Asistencia> result = asistenciaService.listar();

        assertEquals(2, result.size());
        assertEquals("2023-10-01", result.get(0).getFecha());
        assertEquals("2023-10-02", result.get(1).getFecha());
    }

    @Test
    void eliminarAsistencia() {
        int id = 1;
        Asistencia asistencia = new Asistencia();
        asistencia.setId(id);
        when(asistenciaRepository.findById(id)).thenReturn(asistencia);

        Map<String, Boolean> resultado = asistenciaService.eliminarAsistencia(
                id);

        assertEquals(Boolean.TRUE, resultado.get("asistencia eliminada"));
    }

    @Test
    void eliminarNada() {
        int id = 1;
        when(asistenciaRepository.findById(id)).thenReturn(null);

        Map<String, Boolean> resultado = asistenciaService.eliminarAsistencia(
                id);

        assertEquals(Boolean.FALSE, resultado.get("Asistencia no encontrada"));
    }

    @Test
    void modificarAsistenciaExistente() {

        Asistencia asistencia = new Asistencia();
        asistencia.setId(1);
        asistencia.setNombre("Nueva Asistencia");
        asistencia.setPorcentaje(75);
        asistencia.setFecha("2023-10-01");

        Asistencia asistenciamodificada = new Asistencia();
        asistenciamodificada.setId(1); // Cambiado a 1
        asistenciamodificada.setNombre("base de datos");
        asistenciamodificada.setPorcentaje(60);
        asistenciamodificada.setFecha("2025-10-2");

        when(asistenciaRepository.findById(asistencia.getId())).thenReturn(asistencia);
        when(asistenciaRepository.existsById(asistencia.getId())).thenReturn(true);
        String resultado = asistenciaService.actualizarAsistencia(asistencia.getId(), asistenciamodificada);

        assertEquals("Asistencia actualizado con exito!", resultado);

    }

    @Test
    void modificarAsistenciaNoExistente() {
        int id = 1;
        Asistencia asistencia = new Asistencia();
        asistencia.setNombre("Nueva Asistencia");
        asistencia.setPorcentaje(75);
        asistencia.setFecha("2023-10-01");

        when(asistenciaRepository.existsById(id)).thenReturn(false);

        String resultado = asistenciaService.actualizarAsistencia(
                id,
                asistencia);

        assertEquals("asistencia no existe", resultado);
    }

}
