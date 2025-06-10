//Creado Por Sergio Puebla

package com.edutech.edutech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech.dto.AdministradorRolDto;
import com.edutech.edutech.model.Administrador;
import com.edutech.edutech.model.Rol;
import com.edutech.edutech.repository.AdministradorRepository;
import com.edutech.edutech.repository.RolRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private RolRepository rolRepository;

    // Almacenar un nuevo administrador
    public String almacenar(Administrador administrador) {
        if (administradorRepository.existsByRut(administrador.getRut())) {
            return "El administrador ya existe";
        } else {
            administradorRepository.save(administrador);
            return "Administrador almacenado con éxito";
        }
    }

    // Listar todos los administradores
    public List<Administrador> listar() {
        return administradorRepository.findAll();
    }

    // Modificar un administrador existente
    public String modificar(String rut, Administrador adminActualizado) {
        Administrador admin = administradorRepository.findByRut(rut);
        if (admin == null) {
            return "Administrador no encontrado";
        }
        admin.setNombre(adminActualizado.getNombre());
        admin.setApellido(adminActualizado.getApellido());
        admin.setContrasena(adminActualizado.getContrasena());
        // Agrega aquí otros campos que quieras actualizar
        administradorRepository.save(admin);
        return "Administrador actualizado con éxito";
    }

    // Eliminar un administrador
    public Map<String, Boolean> eliminar(String Rut) {
        Map<String, Boolean> respuesta = new HashMap<>();
        Administrador admin = administradorRepository.findByRut(Rut);
        if (admin != null) {
            // if para borrar desvincular admin de la sede
            if (admin.getSede() != null) {
                admin.getSede().setAdministrador(null);
                admin.setSede(null);
            }
            administradorRepository.save(admin);
            administradorRepository.delete(admin);
            respuesta.put("administrador eliminado", Boolean.TRUE);
        } else {
            respuesta.put("administrador no encontrado", Boolean.FALSE);
        }
        return respuesta;
    }

    // Asignar rol a administrador
    public String asignarRol(String Rut, int Id) {
        Administrador admin = administradorRepository.findByRut(Rut);
        Rol rol = rolRepository.findById(Id).orElse(null);
        if (admin == null || rol == null) {
            return "Administrador o rol no encontrado";
        }
        admin.setRol(rol);
        administradorRepository.save(admin);
        return "Rol asignado al administrador";
    }

    // DTO
    public String asignarRolDto(AdministradorRolDto dto) {
        Administrador admin = administradorRepository.findByRut(dto.getRut());
        Rol rol = rolRepository.findById(dto.getId()).orElse(null);
        if (admin == null || rol == null) {
            return "Administrador o rol no encontrado";
        }
        admin.setRol(rol);
        administradorRepository.save(admin);
        return "Rol asignado al administrador";
    }
}