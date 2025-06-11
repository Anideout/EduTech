package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.service.NotificacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {
    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    void almacenarExistente() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1);
        notificacion.setMensaje("hola");

        when(notificacionRepository.existsById(notificacion.getId())).thenReturn(true);

        String resultado = notificacionService.almacenar(notificacion);

        assertEquals("notificacion ya existe!", resultado);
    }

    @Test
    void almacenarNuevo() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1);
        notificacion.setMensaje("hola");

        String resultado = notificacionService.almacenar(notificacion);

        assertEquals("notificacion almacenada con exito!", resultado);
    }

    @Test
    void listar() {
        Notificacion n1 = new Notificacion();
        n1.setId(1);
        n1.setMensaje("Mensaje 1");

        Notificacion n2 = new Notificacion();
        n2.setId(2);
        n2.setMensaje("Mensaje 2");

        when(notificacionRepository.findAll()).thenReturn(List.of(n1, n2));

        List<Notificacion> resultado = notificacionService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Mensaje 1", resultado.get(0).getMensaje());
        assertEquals("Mensaje 2", resultado.get(1).getMensaje());
    }
}
