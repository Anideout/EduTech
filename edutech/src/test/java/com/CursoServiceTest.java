//creado por sergio puebla
package com;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.ContenidoRepository;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.EvaluacionRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.CursoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void almacenarCursoRepetido() {
        Curso curso = new Curso();
        curso.setSigla("11");
        curso.setNombre("curso 1");

        when(cursoRepository.existsBySigla(curso.getSigla())).thenReturn(true);

        String resultado = cursoService.almacenar(curso);

        assertEquals("NOK", resultado);
    }

    @Test
    void almacenarCursoNuevo() {
        Curso curso = new Curso();
        curso.setSigla("11");
        curso.setNombre("curso 1");

        String resultado = cursoService.almacenar(curso);

        assertEquals("OK", resultado);
    }

    @Test
    void listar() {
        Curso c1 = new Curso();
        c1.setSigla("11");
        c1.setNombre("curso 1");

        Curso c2 = new Curso();
        c2.setSigla("22");
        c2.setNombre("curso 2");

        List<Curso> lista = new ArrayList<>();
        lista.add(c1);
        lista.add(c2);

        when(cursoRepository.findAll()).thenReturn(lista);

        List<Curso> resultado = cursoService.listar();

        assertEquals(2, resultado.size());
        assertEquals("curso 1", resultado.get(0).getNombre());
    }

    @Test
    void eliminarcursoExistente() {
        String sigla = "11";
        Curso curso = new Curso();
        curso.setSigla(sigla);
        curso.setNombre("curso 1");
        curso.setProfesores(new ArrayList<>()); // la lista de profesores
        curso.setUsuarios(new ArrayList<>()); // la lista de usuarios
        when(cursoRepository.findBySigla(sigla)).thenReturn(curso);

        Map<String, Boolean> respuesta = cursoService.eliminarCurso(sigla);
        assertEquals(Boolean.TRUE, respuesta.get("Curso eliminado"));
    }

    @Test
    void eliminarcursoNoExistente() {
        String sigla = "11";
        when(cursoRepository.findBySigla(sigla)).thenReturn(null);

        Map<String, Boolean> resultado = cursoService.eliminarCurso(sigla);

        assertEquals(Boolean.FALSE, resultado.get("Curso no encontrado"));
    }

    @Test
    void modificarcursoExistente() {
        String sigla = "11";
        Curso curso = new Curso();
        curso.setSigla(sigla);
        curso.setNombre("curso 1");

        when(cursoRepository.findBySigla(sigla)).thenReturn(curso);

        curso.setSigla(sigla);
        curso.setNombre("curso modificada");
        curso.setDescripcion("descripcion modificada");
        curso.setEstado("estado modificada");
        curso.setProfesores(new ArrayList<>()); // la lista de profesores
        curso.setUsuarios(new ArrayList<>()); // la lista de usuarios

        String resultado = cursoService.actualizarCurso(sigla, curso);

        assertEquals("Curso actualizado con exito!", resultado);
        assertEquals("curso modificada", curso.getNombre());
        assertEquals("descripcion modificada", curso.getDescripcion());
        assertEquals("estado modificada", curso.getEstado());
    }

    @Test
    void modificarcursoNoExistente() {
        String sigla = "11";
        when(cursoRepository.findBySigla(sigla)).thenReturn(null);

        Curso curso = new Curso();
        curso.setSigla(sigla);
        curso.setNombre("curso modificada");
        curso.setDescripcion("descripcion modificada");
        curso.setEstado("direccion modificada");

        String resultado = cursoService.actualizarCurso(sigla, curso);

        assertEquals("Curso no existe!", resultado);
    }

    // --------------------------- ASIGNACIONES ----------------------------------

    @Test
    void asignarEvaluacionCursoNoExiste() {
        String sigla = "11";
        int id = 1;

        when(cursoRepository.existsBySigla(sigla)).thenReturn(false);

        String resultado = cursoService.asignarEvaluacion(sigla, id);

        assertEquals("El curso ingresado no existe!", resultado);
    }

    @Test
    void asignarEvaluacionEvaluacionNoExiste() {
        String sigla = "11";
        int id = 1;

        when(cursoRepository.existsBySigla(sigla)).thenReturn(true);
        when(contenidoRepository.existsById(id)).thenReturn(false);

        String resultado = cursoService.asignarEvaluacion(sigla, id);

        assertEquals("La evaluacion no existe", resultado);
    }

    @Test
    void asignarContenidoCursoNoExiste() {
        String sigla = "11";
        int id = 1;

        when(cursoRepository.existsBySigla(sigla)).thenReturn(false);

        String resultado = cursoService.asignarContenido(sigla, id);

        assertEquals("El curso ingresado no existe!", resultado);
    }

    @Test
    void asignarContenidoEvaluacionNoExiste() {
        String sigla = "11";
        int id = 1;

        when(cursoRepository.existsBySigla(sigla)).thenReturn(true);
        when(evaluacionRepository.existsById(id)).thenReturn(false);

        String resultado = cursoService.asignarContenido(sigla, id);

        assertEquals("La evaluacion no existe", resultado);
    }

    @Test
    void asignarProfesorCursoNoExiste() {
        String sigla = "11";
        String rut = "123";

        when(cursoRepository.existsBySigla(sigla)).thenReturn(false);

        String resultado = cursoService.AsignarProfesorCurso(sigla, rut);

        assertEquals("el curso ingresado no existe", resultado);
    }

    @Test
    void asignarProfesorProfesorNoExiste() {
        String sigla = "11";
        String rut = "123";

        when(cursoRepository.existsBySigla(sigla)).thenReturn(true);
        when(profesorRepository.existsById(rut)).thenReturn(false);

        String resultado = cursoService.AsignarProfesorCurso(sigla, rut);

        assertEquals("el profesor ingresado no existe", resultado);
    }

    @Test
    void asignarUsuarioCursoNoExiste() {
        String sigla = "11";
        String email = "test@email.com";

        when(cursoRepository.existsBySigla(sigla)).thenReturn(false);

        String resultado = cursoService.AsignarUsuarioCurso(sigla, email);

        assertEquals("el curso ingresado no existe", resultado);
    }

    @Test
    void asignarUsuarioUsuarioNoExiste() {
        String sigla = "11";
        String email = "test@email.com";

        when(cursoRepository.existsBySigla(sigla)).thenReturn(true);
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        String resultado = cursoService.AsignarUsuarioCurso(sigla, email);

        assertEquals("el usuario ingresado no existe", resultado);
    }
}
