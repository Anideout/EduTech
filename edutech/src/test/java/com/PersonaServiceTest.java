package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.edutech.dto.PersonaDTO;
import com.edutech.edutech.model.Persona;
import com.edutech.edutech.repository.PersonaRepository;
import com.edutech.edutech.service.PersonaService;

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
        persona.setNombre("persona 1");

        when(personaRepository.existsById(persona.getRut())).thenReturn(true);

        String resultado = personaService.almacenar(persona);

        assertEquals("NOK", resultado);
    }

    @Test
    void almacenarPersonaNueva() {
        Persona persona = new Persona();
        persona.setRut("11.111.111-1");
        persona.setNombre("persona 1");

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

    @Test
    void eliminarPersonaExistente() {
        String rut = "11.111.111-1";
        Persona persona = new Persona();
        persona.setRut(rut);
        persona.setNombre("persona 1");
        when(personaRepository.findByRut(rut)).thenReturn(persona);

        Map<String, Boolean> respuesta = personaService.eliminarPersona(rut);
        assertEquals(Boolean.TRUE, respuesta.get("persona eliminada"));
    }

    @Test
    void eliminarPersonaNoExistente() {
        String rut = "11.111.111-1";
        when(personaRepository.findByRut(rut)).thenReturn(null);

        Map<String, Boolean> resultado = personaService.eliminarPersona(rut);

        assertEquals(Boolean.FALSE, resultado.get("persona no encontrada"));
    }

    @Test
    void modificarPersonaExistente() {
        String rut = "11.111.111-1";
        Persona persona = new Persona();
        persona.setRut(rut);
        persona.setNombre("persona 1");

        when(personaRepository.findByRut(rut))
                .thenReturn(persona);

        PersonaDTO dto = new PersonaDTO();
        dto.setRut(rut);
        dto.setNombre("persona modificada");
        dto.setApellido("apellido modificado");
        dto.setDireccion("direccion modificada");

        String resultado = personaService.modificar(dto);

        assertEquals("persona actualizada correctamente", resultado);
        assertEquals("persona modificada", persona.getNombre());
        assertEquals("apellido modificado", persona.getApellido());
        assertEquals("direccion modificada", persona.getDireccion());
    }

    @Test
    void modificarPersonaNoExistente() {
        String rut = "11.111.111-1";
        when(personaRepository.findByRut(rut)).thenReturn(null);

        PersonaDTO dto = new PersonaDTO();
        dto.setRut(rut);
        dto.setNombre("persona modificada");
        dto.setApellido("apellido modificado");
        dto.setDireccion("direccion modificada");

        String resultado = personaService.modificar(dto);

        assertEquals(" persona con ese rut no existe", resultado);
    }
}
