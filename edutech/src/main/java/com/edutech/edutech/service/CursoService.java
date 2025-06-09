//Creado por Sergio Puebla

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.ActualizarCursoDto;
import com.edutech.edutech.dto.AsignarCursoDto;
import com.edutech.edutech.model.Contenido;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.ContenidoRepository;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.EvaluacionRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private ContenidoRepository contenidoRepository;

    public String almacenar(Curso curso) {
        Curso validacion = cursoRepository.findBySigla(curso.getSigla());
        if (validacion != null) {
            return "El curso ya existe";
        } else {
            cursoRepository.save(curso);
            return "OK";
        }
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public List<Curso> buscar(String nombre) {
        return cursoRepository.findByNombreContaining(nombre);
    }

    public String actualizarCurso(String sigla, Curso cursoActualizado) {
        Curso curso = cursoRepository.findBySigla(sigla);
        if (curso != null) {
            curso.setSigla(cursoActualizado.getSigla());
            curso.setNombre(cursoActualizado.getNombre());
            curso.setDescripcion(cursoActualizado.getDescripcion());
            curso.setEstado(cursoActualizado.getEstado());
            curso.setValor(cursoActualizado.getValor());
            cursoRepository.save(curso);
            return "Curso actualizado con exito!";
        } else {
            return "Curso no existe!";
        }
    }

    // Creado por matias borquez
    // @DeleteMapping("/eliminarCurso/{rut}")
    public Map<String, Boolean> eliminarCurso(String sigla) {
        Curso curso = cursoRepository.findBySigla(sigla);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (curso != null) {
            // elimina las relaciones entre curso y profesor
            // en la lista de profesores del curso
            for (Profesor profesor : curso.getProfesores()) {
                profesor.getCursos().remove(curso);
                profesorRepository.save(profesor);
            }
            curso.getProfesores().clear();
            for (Usuario usuario : curso.getUsuarios()) {
                usuario.getCursos().remove(curso);
                usuarioRepository.save(usuario);
            }
            // elimina las relaciones entre curso y usuario
            curso.getUsuarios().clear();

            cursoRepository.save(curso);
            cursoRepository.delete(curso);
            respuesta.put("Curso eliminado", Boolean.TRUE);

        } else {
            respuesta.put("Curso no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }

    // ------------------ASIGNACIONES ----------------------------

    public String asignarEvaluacion(String sigla, int id) {
        if (!cursoRepository.existsBySigla(sigla)) {
            return "El curso ingresado no existe!";
        } else if (!contenidoRepository.existsById(id)) {
            return "La evaluacion no existe";
        }
        Curso curso = cursoRepository.findBySigla(sigla);
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElse(null);

        curso.setEvaluacion(evaluacion);
        cursoRepository.save(curso);

        return "Evaluacion asignada correctamente al curso";

    }

    public String asignarContenido(String sigla, int id) {
        if (!cursoRepository.existsBySigla(sigla)) {
            return "El curso ingresado no existe!";
        } else if (!evaluacionRepository.existsById(id)) {
            return "La evaluacion no existe";
        }
        Curso curso = cursoRepository.findBySigla(sigla);
        Contenido contenido = contenidoRepository.findById(id);
        if (contenido == null) {
            return "el contenido no existe";
        }
        curso.setContenido(contenido);
        cursoRepository.save(curso);

        return "Contenido asignado correctamente al curso";

    }

    public String AsignarProfesorCurso(String sigla, String rut) {
        if (!cursoRepository.existsBySigla(sigla)) {
            return "el curso ingresado no existe";
        } else if (!profesorRepository.existsById(rut)) {
            return "el profesor ingresado no existe";
        }
        Curso curso = cursoRepository.findBySigla(sigla);
        Profesor profesor = profesorRepository.findById(rut).orElse(null);

        curso.getProfesores().add(profesor);
        cursoRepository.save(curso);

        profesor.getCursos().add(curso);
        profesorRepository.save(profesor);
        return "Profesor asignado correctamente al curso";

    }

    public String AsignarUsuarioCurso(String sigla, String email) {
        if (!cursoRepository.existsBySigla(sigla)) {
            return "el curso ingresado no existe";
        } else if (!usuarioRepository.existsByEmail(email)) {
            return "el usuario ingresado no existe";
        }
        // si aseguramos que ambos existen, entonces asignamos el usuario al curso
        Curso curso = cursoRepository.findBySigla(sigla);
        Usuario usuario = usuarioRepository.findByEmail(email);

        curso.getUsuarios().add(usuario);
        cursoRepository.save(curso);

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);
        return "usuario asignado correctamente al curso";

    }

    // -------------------------- DTO -------------------------

    public String evaluacion(AsignarCursoDto dto) {
        if (!cursoRepository.existsBySigla(dto.getSigla())) {
            return "El curso ingresado no existe!";
        } else if (!evaluacionRepository.existsById(dto.getId())) {
            return "La evaluacion no existe";
        }
        Curso curso = cursoRepository.findBySigla(dto.getSigla());
        Evaluacion evaluacion = evaluacionRepository.findById(dto.getId()).orElse(null);

        curso.setEvaluacion(evaluacion);
        cursoRepository.save(curso);

        return "Evaluacion asignada correctamente al curso";

    }

    public String contenido(AsignarCursoDto dto) {
        if (!cursoRepository.existsBySigla(dto.getSigla())) {
            return "El curso ingresado no existe!";
        } else if (!contenidoRepository.existsById(dto.getId())) {
            return "La evaluacion no existe";
        }
        Curso curso = cursoRepository.findBySigla(dto.getSigla());
        Contenido contenido = contenidoRepository.findById(dto.getId());

        curso.setContenido(contenido);
        cursoRepository.save(curso);

        return "Contenido asignado correctamente al curso";

    }

    public String profesor(AsignarCursoDto dto) {
        if (!cursoRepository.existsBySigla(dto.getSigla())) {
            return "el curso ingresado no existe";
        } else if (!profesorRepository.existsByRut(dto.getRut())) {
            return "el profesor ingresado no existe";
        }
        Curso curso = cursoRepository.findBySigla(dto.getSigla());
        Profesor profesor = profesorRepository.findByRut(dto.getRut());

        curso.getProfesores().add(profesor);
        cursoRepository.save(curso);

        profesor.getCursos().add(curso);
        profesorRepository.save(profesor);
        return "Profesor asignado correctamente al curso";

    }

    public String usuario(AsignarCursoDto dto) {
        if (!cursoRepository.existsBySigla(dto.getSigla())) {
            return "el curso ingresado no existe";
        } else if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "el usuario ingresado no existe";
        }
        // si aseguramos que ambos existen, entonces asignamos el usuario al curso
        Curso curso = cursoRepository.findBySigla(dto.getSigla());
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

        curso.getUsuarios().add(usuario);
        cursoRepository.save(curso);

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);
        return "usuario asignado correctamente al curso";

    }

    public String actualizar(ActualizarCursoDto dto) {
        Curso curso = cursoRepository.findBySigla(dto.getSigla());
        if (curso != null) {
            curso.setSigla(dto.getSigla());
            curso.setNombre(dto.getNombre());
            curso.setDescripcion(dto.getDescripcion());
            curso.setEstado(dto.getEstado());
            curso.setValor(dto.getValor());
            cursoRepository.save(curso);
            return "Curso actualizado con exito!";
        } else {
            return "Curso no existe!";
        }
    }

}
