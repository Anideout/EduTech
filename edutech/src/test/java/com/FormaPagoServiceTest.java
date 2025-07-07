package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.FormaPago;
import com.edutech.edutech.repository.FormaPagoRepository;
import com.edutech.edutech.service.FormaPagoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FormaPagoServiceTest {

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @InjectMocks
    private FormaPagoService formaPagoService;

    @Test
    void almacenarFormaPagoRepetida() {
        FormaPago formaPago = new FormaPago();
        formaPago.setId(1);
        formaPago.setNombre("Efectivo");

        when(formaPagoRepository.existsById(formaPago.getId())).thenReturn(
            true
        );

        String resultado = formaPagoService.almacenar(formaPago);

        assertEquals("La forma de pago ya existe", resultado);
    }

    @Test
    void almacenarFormaPagoNueva() {
        FormaPago formaPago = new FormaPago();
        formaPago.setId(1);
        formaPago.setNombre("Efectivo");

        String resultado = formaPagoService.almacenar(formaPago);
        assertEquals("Forma de pago almacenada correctamente", resultado);
    }

    @Test
    void listar() {
        FormaPago f1 = new FormaPago();
        f1.setId(1);
        f1.setNombre("Efectivo");
        FormaPago f2 = new FormaPago();
        f2.setId(2);
        f2.setNombre("Tarjeta");

        when(formaPagoRepository.findAll()).thenReturn(
            java.util.List.of(f1, f2)
        );

        var resultado = formaPagoService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Efectivo", resultado.get(0).getNombre());
        assertEquals("Tarjeta", resultado.get(1).getNombre());
    }

    @Test
    void modificarFormaPagoExistente() {
        FormaPago original = new FormaPago();
        original.setId(1);
        original.setNombre("Antigua");

        FormaPago actualizado = new FormaPago();
        actualizado.setNombre("Nueva");

        when(formaPagoRepository.findById(1)).thenReturn(
            java.util.Optional.of(original)
        );

        String resultado = formaPagoService.modificar(1, actualizado);

        assertEquals("forma de pago modificada!", resultado);
        assertEquals("Nueva", original.getNombre());
    }

    @Test
    void modificarFormaPagoNoExistente() {
        FormaPago actualizado = new FormaPago();
        actualizado.setNombre("Nueva");

        when(formaPagoRepository.findById(1)).thenReturn(
            java.util.Optional.empty()
        );

        String resultado = formaPagoService.modificar(1, actualizado);

        assertEquals("forma de pago no encontrada...", resultado);
    }

    @Test
    void eliminarFormaPagoExistente() {
        when(formaPagoRepository.existsById(1)).thenReturn(true);

        var resultado = formaPagoService.eliminar(1);

        assertEquals(Boolean.TRUE, resultado.get("Forma de pago eliminada..."));
    }

    @Test
    void eliminarFormaPagoNoExistente() {
        when(formaPagoRepository.existsById(1)).thenReturn(false);

        var resultado = formaPagoService.eliminar(1);

        assertEquals(
            Boolean.FALSE,
            resultado.get("forma de pago no existe...")
        );
    }
}
