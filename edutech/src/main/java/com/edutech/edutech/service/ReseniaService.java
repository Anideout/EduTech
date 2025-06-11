//Creado por Matías Borquez

package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Resenia;
import com.edutech.edutech.repository.ReseniaRepository;

@Service
public class ReseniaService {
    @Autowired
    private ReseniaRepository reseniaRepository;

    public String almacenar(Resenia resenia) {
        if (reseniaRepository.existsById(resenia.getId())) {
            return "resenia ya existe";
        } else {
            reseniaRepository.save(resenia);
            return "resenia guardada con exito!";
        }

    }

    public List<Resenia> listar() {
        return reseniaRepository.findAll();
    }

    public String modificar(int id, Resenia reseniaModificada) {
        Resenia resenia = reseniaRepository.findById(id).orElse(null);
        if (resenia == null) {
            return "resenia no encontrada";
        }
        resenia.setDescripcion(reseniaModificada.getDescripcion());
        resenia.setFecha(reseniaModificada.getFecha());
        reseniaRepository.save(resenia);
        return "Reseña guardada con exito!";
    }

    public String eliminar(int id) {
        Resenia resenia = reseniaRepository.findById(id).orElse(null);
        if (resenia == null) {
            return "reseña no encontrada";
        }
        reseniaRepository.delete(resenia);
        return "Reseña eliminada con exito!";
    }
}
