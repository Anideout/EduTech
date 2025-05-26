package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.Contenido;
import com.edutech.edutech.repository.ContenidoRepository;

@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;

    public String almacenar(Contenido contenido) {
        if (contenidoRepository.existsById(contenido.getId())) {
            return "el rut ingresado ya existe! ";

        } else {
            contenidoRepository.save(contenido);
            return "contenido almacenado";
        }
    }

    public List<Contenido> listar() {
        return contenidoRepository.findAll();
    }

    public List<Contenido> buscar(String titulo) {
        return contenidoRepository.findByTituloContaining(titulo);
    }

    public String actualizarContenido(int id, Contenido contenidoActualizado) {
        Contenido contenido = contenidoRepository.findById(id);
        if (contenido != null) {
            contenido.setId(contenidoActualizado.getId());
            contenido.setTitulo(contenidoActualizado.getTitulo());
            contenido.setDescripcion(contenidoActualizado.getDescripcion());
            contenido.setEstado(contenidoActualizado.getEstado());
            contenidoRepository.save(contenido);
            return "contenido actualizado con exito!";
        } else {
            return "rut del contenido no existe!";
        }
    }

    // @DeleteMapping("/Personas/{rut}")
    public Map<String, Boolean> eliminarcontenido(int id) {
        Contenido contenido = contenidoRepository.findById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        if (contenido != null) {
            contenidoRepository.delete(contenido);
            respuesta.put("contenido eliminado", Boolean.TRUE);

        } else {
            respuesta.put("contenido no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }
}
