//creado por matías borquez

package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.repository.ReseniaRepository;
import com.edutech.edutech.service.ReseniaService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReseniaServiceTest {

    @Mock
    private ReseniaRepository reseniaRepository;

    @InjectMocks
    private ReseniaService reseniaService;

    @Test
    void almacenarExistente() {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        resenia.setDescripcion("descr");

        when(reseniaRepository.existsById(resenia.getId())).thenReturn(true);

        String resultado = reseniaService.almacenar(resenia);

        assertEquals("resenia ya existe", resultado);
    }

    @Test
    void almacenarNuevoa() {
        Resenia resenia = new Resenia();
        resenia.setId(0);
        resenia.setDescripcion("descr");

        String resultado = reseniaService.almacenar(resenia);

        assertEquals("resenia guardada con exito!", resultado);
    }

    @Test
    void listar() {
        Resenia r1 = new Resenia();
        r1.setId(1);
        r1.setDescripcion("hola");

        Resenia r2 = new Resenia();
        r2.setId(2);
        r2.setDescripcion("hola 2");

        when(reseniaRepository.findAll()).thenReturn(List.of(r1, r2));

        List<Resenia> resultado = reseniaService.listar();

        assertEquals(2, resultado.size());
        assertEquals("hola", resultado.get(0).getDescripcion());
        assertEquals("hola 2", resultado.get(1).getDescripcion());
    }

    @Test
    void modificarReseniaExistente() {
        Resenia original = new Resenia();
        original.setId(1);
        original.setDescripcion("Vieja");
        original.setFecha("2024-06-01");

        Resenia modificada = new Resenia();
        modificada.setDescripcion("Nueva");
        modificada.setFecha("2024-06-02");

        // Simula que existe la reseña
        when(reseniaRepository.findById(1)).thenReturn(
            java.util.Optional.of(original)
        );

        String resultado = reseniaService.modificar(1, modificada);

        assertEquals("Reseña guardada con exito!", resultado);
    }

    @Test
    void modificarReseniaNoExistente() {
        Resenia modificada = new Resenia();
        modificada.setDescripcion("Nueva");
        modificada.setFecha("2024-06-02");

        // Simula que NO existe la reseña
        when(reseniaRepository.findById(1)).thenReturn(
            java.util.Optional.empty()
        );

        String resultado = reseniaService.modificar(1, modificada);

        assertEquals("resenia no encontrada", resultado);
    }

    @Test
    void eliminarReseniaExistente() {
        Resenia resenia = new Resenia();
        resenia.setId(1);

        when(reseniaRepository.findById(1)).thenReturn(
            java.util.Optional.of(resenia)
        );

        String resultado = reseniaService.eliminar(1);

        assertEquals("Reseña eliminada con exito!", resultado);
    }

    @Test
    void eliminarReseniaNoExistente() {
        when(reseniaRepository.findById(1)).thenReturn(
            java.util.Optional.empty()
        );

        String resultado = reseniaService.eliminar(1);

        assertEquals("reseña no encontrada", resultado);
    }
}
