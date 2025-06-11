package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Contenido;
import com.edutech.edutech.repository.ContenidoRepository;
import com.edutech.edutech.service.ContenidoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContenidoServiceTest {
    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoService contenidoService;

    @Test
    public void almacenarContenidoExistente() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Titulo");

        when(contenidoRepository.existsById(contenido.getId())).thenReturn(true);

        String resultado = contenidoService.almacenar(contenido);

        assertEquals("el contenido ingresado ya existe!", resultado);
    }

    @Test
    public void almacenarContenidoNuevo() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Titulo 1");

        String resultado = contenidoService.almacenar(contenido);

        assertEquals("contenido almacenado", resultado);
    }

}
