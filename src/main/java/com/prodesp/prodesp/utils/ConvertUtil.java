/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.utils;


import com.prodesp.prodesp.dtos.CategoriasDTO;
import com.prodesp.prodesp.dtos.TarefasDTO;
import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.entities.Tarefas;
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
    
    public CategoriasDTO convertToDto(Categorias categoria) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoria, CategoriasDTO.class);
    }

    public Categorias convertToEntity(CategoriasDTO categoriaDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoriaDto, Categorias.class);
    }
    
    public TarefasDTO convertToDto(Tarefas tarefa) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tarefa, TarefasDTO.class);
    }

    public Tarefas convertToEntity(TarefasDTO tarefaDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tarefaDto, Tarefas.class);
    }    
}
