//creado matías borquez
package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Notificacion;
import com.edutech.edutech.repository.NotificacionRepository;
import com.edutech.edutech.service.NotificacionService;
import java.util.List;
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

        when(
            notificacionRepository.existsById(notificacion.getId())
        ).thenReturn(true);

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

    @Test
    void actualizarnotificacionExistente() {
        Notificacion original = new Notificacion();
        original.setId(1);
        original.setMensaje("Viejo mensaje");
        original.setFecha("2024-06-01");

        Notificacion actualizado = new Notificacion();
        actualizado.setMensaje("Mensaje nuevo");
        actualizado.setFecha("2024-06-02");

        when(notificacionRepository.findById(1)).thenReturn(original);

        String resultado = notificacionService.actualizarnotificacion(
            1,
            actualizado
        );

        assertEquals("notificacion actualizado con exito!", resultado);
        // Si querís sapear que se actualizó, podís revisar los setters, pero como es mock, no cambia el objeto real
    }

    @Test
    void actualizarnotificacionNoExistente() {
        Notificacion actualizado = new Notificacion();
        actualizado.setMensaje("Mensaje nuevo");
        actualizado.setFecha("2024-06-02");

        when(notificacionRepository.findById(1)).thenReturn(null);

        String resultado = notificacionService.actualizarnotificacion(
            1,
            actualizado
        );

        assertEquals("rut del notificacion no existe!", resultado);
    }

    @Test
    void eliminarNotificacionExistente() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1);

        when(notificacionRepository.findById(1)).thenReturn(notificacion);

        var resultado = notificacionService.eliminarNotificacion(1);

        assertEquals(Boolean.TRUE, resultado.get("notificacion eliminado"));
    }

    @Test
    void eliminarNotificacionNoExistente() {
        when(notificacionRepository.findById(1)).thenReturn(null);

        var resultado = notificacionService.eliminarNotificacion(1);

        assertEquals(
            Boolean.FALSE,
            resultado.get("notificacion no encontrado")
        );
    }
}
