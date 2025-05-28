//Creado por Matías Borquez
package com.edutech.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.model.FormaPago;
import com.edutech.edutech.model.Tarjeta;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.FormaPagoRepository;
import com.edutech.edutech.repository.TarjetaRepository;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    public String almacenar(Tarjeta tarjeta, String email, int idFormaPago) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        FormaPago formaPago = formaPagoRepository.findById(idFormaPago).orElse(null);

        if (usuario == null || formaPago == null) {
            return "usuario y/o forma de pago no encontrada...";
        }
        tarjeta.setUsuario(usuario);
        tarjeta.setFormaPago(formaPago);
        tarjetaRepository.save(tarjeta);
        return "Tarjeta almacenada...";
    }

    public List<Tarjeta> listar() {
        return tarjetaRepository.findAll();
    }

    public String eliminar(String nroTarjeta) {
        Tarjeta tarjeta = tarjetaRepository.findById(nroTarjeta).orElse(null);
        if (tarjeta == null) {
            return "tarjeta no encontrada";
        }
        tarjetaRepository.delete(tarjeta);
        return "tarjeta eliminada con éxito!";
    }

}
