/*
 * package com;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import static org.mockito.Mockito.when;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 * import com.edutech.edutech.model.Curso;
 * import com.edutech.edutech.repository.CursoRepository;
 * import com.edutech.edutech.service.CursoService;
 * 
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith;
 * import org.mockito.InjectMocks;
 * import org.mockito.Mock;
 * import org.mockito.junit.jupiter.MockitoExtension;
 * 
 * @ExtendWith(MockitoExtension.class)
 * public class CursoServiceTest {
 * 
 * @Mock
 * private CursoRepository cursoRepository;
 * 
 * @InjectMocks
 * private CursoService cursoService;
 * 
 * @Test
 * void testAlmacenarCurso() {
 * Curso curso = new Curso();
 * curso.setSigla("11");
 * curso.setNombre("Curso 1");
 * 
 * when(cursoRepository.existsBySigla(curso.getSigla())).thenReturn(false);
 * when(cursoRepository.save(curso)).thenReturn(curso);
 * String resultado = cursoService.almacenar(curso);
 * 
 * assertEquals("OK", resultado);
 * }
 * 
 * @Test
 * void listar() {
 * Curso c1 = new Curso();
 * c1.setSigla("11");
 * c1.setNombre("Curso 1");
 * 
 * Curso c2 = new Curso();
 * c2.setSigla("22");
 * c2.setNombre("Curso 2");
 * 
 * List<Curso> lista = new ArrayList<>();
 * lista.add(c1);
 * lista.add(c2);
 * 
 * when(cursoRepository.findAll()).thenReturn(lista);
 * 
 * List<Curso> resultado = cursoService.listar();
 * 
 * assertEquals(2, resultado.size());
 * assertEquals("Curso 1", resultado.get(0).getNombre());
 * assertEquals("Curso 2", resultado.get(1).getNombre());
 * 
 * }
 * }
 */
