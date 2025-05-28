//Creado por Matías Borquez

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.RolDto;
import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Rol;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.RolRepository;
import com.edutech.edutech.repository.UsuarioRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private AdministradorRepository administradorRepository;

    // Almacenar un nuevo rol
    public String almacenar(Rol rol) {
        if (rolRepository.existsById(rol.getId())) {
            return "El rol ya existe";
        }
        rolRepository.save(rol);
        return "Rol almacenado con éxito";
    }

    public String buscar(String nombre) {
        Rol rol = rolRepository.findByNombreContaining(nombre);
        if (rol == null) {
            return "Rol no encontrado";
        }
        return "Rol encontrado: " + rol.getNombre();
    }

    // Listar todos los roles
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    // Modificar un rol existente
    public String modificar(int id, Rol rolActualizado) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol == null) {
            return "Rol no encontrado";
        }
        rol.setNombre(rolActualizado.getNombre());
        rolRepository.save(rol);
        return "Rol actualizado con éxito";
    }

    // Eliminar un rol
    public Map<String, Boolean> eliminar(int id) {
        Map<String, Boolean> respuesta = new HashMap<>();
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            // Limpia relaciones si es necesario
            rolRepository.delete(rol);
            respuesta.put("rol eliminado", Boolean.TRUE);
        } else {
            respuesta.put("rol no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }

    // Asignar rol a usuario
    public String asignarRolAUsuario(int id, String email) {
        if (!rolRepository.existsById(id)) {
            return "rol no existe";
        } else if (!usuarioRepository.existsByEmail(email)) {
            return "usuario no existe!";
        }
        Rol rol = rolRepository.findById(id).orElse(null);
        Usuario usuario = usuarioRepository.findByEmail(email);

        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "Rol asignado al usuario con éxito";
    }

    // Asignar rol a profesor
    public String asignarRolAProfesor(int id, String rut) {
        if (!rolRepository.existsById(id)) {
            return "rol no existe";
        } else if (!profesorRepository.existsByRut(rut)) {
            return "profesor no existe!";
        }
        Rol rol = rolRepository.findById(id).orElse(null);
        Profesor profesor = profesorRepository.findByRut(rut);
        profesor.setRol(rol);
        profesorRepository.save(profesor);
        return "Rol asignado al profesor";
    }

    // Asignar rol a administrador
    public String asignarRolAAdministrador(int id, String rut) {
        if (!rolRepository.existsById(id)) {
            return "rol no existe";
        } else if (!administradorRepository.existsByRut(rut)) {
            return "administrador no existe!";
        }
        Rol rol = rolRepository.findById(id).orElse(null);
        Administrador admin = administradorRepository.findByRut(rut);

        admin.setRol(rol);
        administradorRepository.save(admin);
        return "Rol asignado al administrador";
    }

    // ------------------------- DTO ----------------------------
    public String usuario(RolDto dto) {
        if (!rolRepository.existsById(dto.getId())) {
            return "rol no existe";
        } else if (!usuarioRepository.existsByEmail(dto.getEmail())) {
            return "usuario no existe!";
        }
        Rol rol = rolRepository.findById(dto.getId()).orElse(null);
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "Rol asignado al usuario con éxito";
    }

    // Asignar rol a profesor
    public String profesor(RolDto dto) {
        if (!rolRepository.existsById(dto.getId())) {
            return "rol no existe";
        } else if (!profesorRepository.existsByRut(dto.getRut())) {
            return "profesor no existe!";
        }
        Rol rol = rolRepository.findById(dto.getId()).orElse(null);
        Profesor profesor = profesorRepository.findByRut(dto.getRut());

        profesor.setRol(rol);
        profesorRepository.save(profesor);
        return "Rol asignado al profesor";
    }

    // Asignar rol a administrador
    public String admin(RolDto dto) {
        if (!rolRepository.existsById(dto.getId())) {
            return "rol no existe";
        } else if (!administradorRepository.existsByRut(dto.getRut())) {
            return "administrador no existe!";
        }
        Rol rol = rolRepository.findById(dto.getId()).orElse(null);
        Administrador admin = administradorRepository.findByRut(dto.getRut());

        admin.setRol(rol);
        administradorRepository.save(admin);
        return "Rol asignado al administrador";
    }

    public String modificar(RolDto dto) {
        Rol rol = rolRepository.findById(dto.getId()).orElse(null);
        if (rol == null) {
            return "Rol no encontrado";
        }
        rol.setNombre(dto.getNombre());
        rolRepository.save(rol);
        return "Rol actualizado con éxito";
    }
}