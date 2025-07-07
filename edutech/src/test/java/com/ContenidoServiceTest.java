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

import com.edutech.edutech.model.Contenido;
import com.edutech.edutech.repository.ContenidoRepository;
import com.edutech.edutech.service.ContenidoService;

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

    @Test
    void listar() {
        Contenido contenido1 = new Contenido();
        contenido1.setId(1);
        contenido1.setTitulo("Contenido 1");

        Contenido contenido2 = new Contenido();
        contenido2.setId(2);
        contenido2.setTitulo("Contenido 2");

        when(contenidoRepository.findAll()).thenReturn(
                List.of(contenido1, contenido2));

        List<Contenido> resultado = contenidoService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Contenido 1", resultado.get(0).getTitulo());
        assertEquals("Contenido 2", resultado.get(1).getTitulo());
    }

    @Test
    void eliminarContenidoExistente() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Contenido a eliminar");

        when(contenidoRepository.findById(contenido.getId())).thenReturn(
                contenido);

        Map<String, Boolean> resultado = contenidoService.eliminarcontenido(
                contenido.getId());

        assertEquals(Boolean.TRUE, resultado.get("contenido eliminado"));
    }

    @Test
    void noEliminar() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Contenido a eliminar");

        Map<String, Boolean> resultado = contenidoService.eliminarcontenido(
                contenido.getId());

        assertEquals(Boolean.FALSE, resultado.get("contenido no encontrado"));
    }

    @Test
    void modificar() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Contenido Original");
        contenido.setDescripcion("Descripcion Original");
        contenido.setEstado("Activo");

        Contenido contenidoActualizado = new Contenido();
        contenidoActualizado.setId(1);
        contenidoActualizado.setTitulo("Contenido Modificado");
        contenidoActualizado.setDescripcion("Descripcion Modificada");
        contenidoActualizado.setEstado("Inactivo");

        when(contenidoRepository.findById(contenido.getId())).thenReturn(
                contenido);

        String resultado = contenidoService.actualizarContenido(
                contenido.getId(),
                contenidoActualizado);

        assertEquals("contenido actualizado con exito!", resultado);
    }

    @Test
    void noModificar() {
        Contenido contenido = new Contenido();
        contenido.setId(1);
        contenido.setTitulo("Contenido Original");
        contenido.setDescripcion("Descripcion Original");
        contenido.setEstado("Activo");

        Contenido contenidoActualizado = new Contenido();
        contenidoActualizado.setId(2); // ID diferente

        String resultado = contenidoService.actualizarContenido(contenido.getId(), contenidoActualizado);

        assertEquals("id del contenido no existe!", resultado);
    }

}
