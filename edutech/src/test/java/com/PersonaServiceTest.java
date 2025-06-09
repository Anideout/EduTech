package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.edutech.edutech.model.Persona;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.service.PersonaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {
    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    @Test
    void almacenarPersonaRepetida() {
        Persona persona = new Persona();
        persona.setRut("11.111.111-1");

        when(personaRepository.existsById(persona.getRut())).thenReturn(true);

        String resultado = personaService.almacenar(persona);

        assertEquals("NOK", resultado);
    }

    @Test
    void almacenarPersonaNueva() {
        Persona persona = new Persona();
        persona.setRut("11.111.111-1");
        persona.setNombre("persona 1");

        when(personaRepository.existsById(persona.getRut())).thenReturn(false);

        String resultado = personaService.almacenar(persona);

        assertEquals("OK", resultado);
    }

    @Test
    void listar() {
        Persona p1 = new Persona();
        p1.setRut("11.111.111-1");
        p1.setNombre("persona 1");

        Persona p2 = new Persona();
        p2.setRut("22.222.222-2");
        p2.setNombre("persona 2");

        List<Persona> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);

        when(personaRepository.findAll()).thenReturn(lista);

        List<Persona> resultado = personaService.listar();

        assertEquals(2, resultado.size());
        assertEquals("persona 1", resultado.get(0).getNombre());

    }
}
