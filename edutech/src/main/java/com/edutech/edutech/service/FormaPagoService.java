package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.FormaPago;
import com.edutech.edutech.repository.FormaPagoRepository;

@Service
public class FormaPagoService {
    @Autowired
    private FormaPagoRepository formaPagoRepository;

    public String almacenar(FormaPago formaPago) {
        formaPagoRepository.save(formaPago);
        return "Forma de pago almacenada correctamente";
    }

    public List<FormaPago> listar() {
        return formaPagoRepository.findAll();
    }

    public String modificar(int id, FormaPago formaPagoActualizado) {
        FormaPago forma = formaPagoRepository.findById(id).orElse(null);
        if (forma == null) {
            return "forma de pago no encontrada...";
        }
        forma.setNombre(formaPagoActualizado.getNombre());
        formaPagoRepository.save(forma);
        return "forma de pago modificada!";
    }

    public Map<String, Boolean> eliminar(int id) {
        Map<String, Boolean> respuesta = new HashMap<>();
        if (!formaPagoRepository.existsById(id)) {
            respuesta.put("forma de pago no existe...", Boolean.FALSE);
        } else {
            formaPagoRepository.deleteById(id);
            respuesta.put("Forma de pago eliminada...", Boolean.TRUE);
        }
        return respuesta;
    }
}
