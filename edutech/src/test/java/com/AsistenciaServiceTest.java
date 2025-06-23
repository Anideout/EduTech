//creado por sergio puebla 
package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Asistencia;
import com.edutech.edutech.repository.AsistenciaRepository;
import com.edutech.edutech.service.Asistenciaservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
