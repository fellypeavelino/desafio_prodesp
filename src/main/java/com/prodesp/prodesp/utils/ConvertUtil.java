/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.utils;


import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.entities.Usuarios;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
/**
 *
 * @author Usuario
 */
@Component
public class ConvertUtil {
    public UsuarioDTO convertToDto(Usuarios usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuarios convertToEntity(UsuarioDTO usuarioDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDto, Usuarios.class);
    }
    
}
