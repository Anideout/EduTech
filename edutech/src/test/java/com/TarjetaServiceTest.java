package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.edutech.edutech.model.FormaPago;
import com.edutech.edutech.model.Tarjeta;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.FormaPagoRepository;
import com.edutech.edutech.repository.TarjetaRepository;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.TarjetaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TarjetaServiceTest {

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @InjectMocks
    private TarjetaService tarjetaService;

    @Test
    void listar() {
        Usuario usuario = new Usuario();
        usuario.setEmail("a");

        FormaPago formaPago = new FormaPago();
        formaPago.setId(1);

        Tarjeta tarjeta1 = new Tarjeta();
        tarjeta1.setUsuario(usuario);
        tarjeta1.setFormaPago(formaPago);
        tarjeta1.setTitular("matias");

        Tarjeta tarjeta2 = new Tarjeta();
        tarjeta2.setUsuario(usuario);
        tarjeta2.setFormaPago(formaPago);
        tarjeta2.setTitular("loren"); // o puedes poner otro nombre si quieres diferenciar

        List<Tarjeta> tarjetas = new ArrayList<>(List.of(tarjeta1, tarjeta2));

        when(tarjetaRepository.findAll()).thenReturn(tarjetas);

        List<Tarjeta> resultado = tarjetaService.listar();

        assertEquals(2, resultado.size());
        assertEquals("matias", resultado.get(0).getTitular());
        assertEquals("loren", resultado.get(1).getTitular());
    }

    @Test
    void testAlmacenar_ok() {
        // Armamos la wea
        Tarjeta tarjeta = new Tarjeta();
        Usuario usuario = new Usuario();
        FormaPago formaPago = new FormaPago();

        when(usuarioRepository.findByEmail("elbro@correo.com")).thenReturn(
                usuario);
        when(formaPagoRepository.findById(1)).thenReturn(
                Optional.of(formaPago));
        when(tarjetaRepository.save(tarjeta)).thenReturn(tarjeta);

        // Ejecutamos la magia
        String resultado = tarjetaService.almacenar(
                tarjeta,
                "elbro@correo.com",
                1);

        assertEquals("Tarjeta almacenada...", resultado);
    }

    @Test
    void testAlmacenar_usuarioNoExiste() {
        Tarjeta tarjeta = new Tarjeta();
        when(usuarioRepository.findByEmail("noexiste@correo.com")).thenReturn(
                null);

        String resultado = tarjetaService.almacenar(
                tarjeta,
                "noexiste@correo.com",
                1);

        assertEquals("usuario y/o forma de pago no encontrada...", resultado);
    }

    @Test
    void testAlmacenar_formaPagoNoExiste() {
        Tarjeta tarjeta = new Tarjeta();
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByEmail("elbro@correo.com")).thenReturn(
                usuario);
        when(formaPagoRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = tarjetaService.almacenar(
                tarjeta,
                "elbro@correo.com",
                99);

        assertEquals("usuario y/o forma de pago no encontrada...", resultado);
    }

    @Test
    void testEliminar_ok() {
        Tarjeta tarjeta = new Tarjeta();
        when(tarjetaRepository.findById("1234")).thenReturn(
                Optional.of(tarjeta));

        String resultado = tarjetaService.eliminar("1234");

        assertEquals("tarjeta eliminada con éxito!", resultado);
    }

    @Test
    void testEliminar_noExiste() {
        when(tarjetaRepository.findById("noexiste")).thenReturn(
                Optional.empty());

        String resultado = tarjetaService.eliminar("noexiste");

        assertEquals("tarjeta no encontrada", resultado);
    }
}