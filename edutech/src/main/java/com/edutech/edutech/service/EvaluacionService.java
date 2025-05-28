//Creado por Sergio Puebla

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public String almacenar(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
        return "evaluacion  almacenada con exito!";
    }

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public List<Evaluacion> buscar(String nombre) {
        return evaluacionRepository.findByNombreContaining(nombre);
    }

    public String modificar(int id, Evaluacion evaluacionModificada) {
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElse(null);
        if (evaluacion == null) {
            return "evaluacion no encontrada";
        }
        evaluacion.setNombre(evaluacionModificada.getNombre());
        evaluacionRepository.save(evaluacion);
        return "evaluacion guardada con éxito";
    }

    public Map<String, Boolean> eliminar(int id) {
        Map<String, Boolean> respuesta = new HashMap<>();
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElse(null);
        if (evaluacion != null) {
            // Limpia relaciones si es necesario (si Evaluacion tiene relaciones con otras
            // entidades)
            evaluacionRepository.delete(evaluacion);
            respuesta.put("evaluacion eliminada", Boolean.TRUE);
        } else {
            respuesta.put("evaluacion no encontrada", Boolean.FALSE);
        }
        return respuesta;
    }

}
