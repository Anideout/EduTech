//creado por sergio puebla
package com;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.edutech.model.Inscripcion;
import com.edutech.edutech.repository.InscripcionRepository;
import com.edutech.edutech.service.InscripcionService;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionService inscripcionService;

    @Test
    void almacenarExistente() {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1);
        inscripcion.setNombre("inscripcion");

        when(inscripcionRepository.existsById(inscripcion.getId())).thenReturn(
                true);

        String resultado = inscripcionService.almacenar(inscripcion);

        assertEquals("inscripcion ya existe!", resultado);
    }

    @Test
    void almacenarNuevo() {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1);
        inscripcion.setNombre("inscripcion");

        String resultado = inscripcionService.almacenar(inscripcion);

        assertEquals("Inscripcion almacenada", resultado);
    }

    @Test
    void listar() {
        Inscripcion i1 = new Inscripcion();
        i1.setId(1);
        i1.setNombre("Inscripcion 1");

        Inscripcion i2 = new Inscripcion();
        i2.setId(2);
        i2.setNombre("Inscripcion 2");

        when(inscripcionRepository.findAll()).thenReturn(List.of(i1, i2));

        List<Inscripcion> resultado = inscripcionService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Inscripcion 1", resultado.get(0).getNombre());
        assertEquals("Inscripcion 2", resultado.get(1).getNombre());
    }

    @Test
    void eliminarInscricpcion() {
        int id = 1;
        Inscripcion inscri = new Inscripcion();
        inscri.setId(id);
        inscri.setNombre("especialidad");

        when(inscripcionRepository.findById(id)).thenReturn(
                Optional.of(inscri));
        String resultado = inscripcionService.eliminar(id);

        assertEquals("inscripcion eliminada con exito!", resultado);
    }

    @Test
    void eliminarNada() {
        int id = 1;

        String resultado = inscripcionService.eliminar(id);

        assertEquals(resultado, "inscripcion no encontrada");
    }

    @Test
    void modificar() {
        int id = 1;
        Inscripcion ins = new Inscripcion();
        ins.setId(id);
        ins.setNombre("matias");

        Inscripcion iniAc = new Inscripcion();
        iniAc.setNombre("nombre");

        when(inscripcionRepository.findById(id)).thenReturn(Optional.of(iniAc));

        String resultado = inscripcionService.modificar(id, iniAc);

        assertEquals("Inscripcion modificada con exito", resultado);
        assertEquals("nombre", iniAc.getNombre());
    }

    @Test
    void modificarNadie() {
        int id = 1;
        Inscripcion ini = new Inscripcion();
        ini.setId(id);
        ini.setNombre("matias");

        String resultado = inscripcionService.modificar(id, ini);

        assertEquals("inscripcion no encontrada", resultado);
    }
}
