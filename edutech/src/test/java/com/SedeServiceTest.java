//creado por matías borquez

package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Sede;
import com.edutech.edutech.repository.SedeRepository;
import com.edutech.edutech.service.SedeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SedeServiceTest {
    @Mock
    private SedeRepository sedeRepository;

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
}
