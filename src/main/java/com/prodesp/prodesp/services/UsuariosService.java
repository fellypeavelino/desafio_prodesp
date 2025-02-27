/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.UsuariosRepository;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class UsuariosService {
    @Autowired
    private ConvertUtil convertUtil;
    private final UsuariosRepository usuarioRepository;
    
    public UsuariosService(UsuariosRepository usuarioRepository) { 
        this.usuarioRepository = usuarioRepository; 
    }
    
    public List<Usuarios> findAll() { 
        return usuarioRepository.findAll(); 
    }

    public List<UsuarioDTO> findDTOAll() { 
        List<Usuarios> list = findAll();
        List<UsuarioDTO> listResult = new ArrayList<>();
        list.forEach(o -> listResult.add(convertUtil.convertToDto(o)));
        return listResult;
    }
    
    public Usuarios save(Usuarios usuario) { 
        return usuarioRepository.save(usuario); 
    }

    public UsuarioDTO saveDTO(UsuarioDTO usuarioDto) { 
        Usuarios usuario = new Usuarios();
        usuario.setLoguin(usuarioDto.getLoguin());
        usuario.setSenha(usuarioDto.getSenha());
        usuario = usuarioRepository.save(usuario); 
        return convertUtil.convertToDto(usuario);
    }
    
    public UsuarioDTO updateDTO(Long id, UsuarioDTO usuarioDto) {
        usuarioDto.setId(id);
        Usuarios usuario = convertUtil.convertToEntity(usuarioDto);
        usuario = usuarioRepository.save(usuario); 
        return convertUtil.convertToDto(usuario);
    }
    
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
    
    public Optional<Usuarios> encontrarPorLoguinESenha(String loguin, String senha) {
        return usuarioRepository.findByLoguinAndSenha(loguin, senha);
    }
    
    public UsuarioDTO encontrarPorLoguinESenhaDTO(UsuarioDTO dto) {
        Optional<Usuarios> op = this.encontrarPorLoguinESenha(dto.getLoguin(), dto.getSenha());
        if (op.isPresent()) {
            dto = convertUtil.convertToDto(op.get());
        }
        return dto;
    }
    
    public UsuarioDTO findDTOById(Long id){
        UsuarioDTO dto = new UsuarioDTO();
        Optional<Usuarios> op = usuarioRepository.findById(id);
        if (op.isPresent()) {
            dto = convertUtil.convertToDto(op.get());
        }
        return dto;
    }
}
