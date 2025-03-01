/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.dtos.UsuarioPaginadosDTO;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.services.UsuariosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    
    private final UsuariosService usuarioService;
    
    public UsuariosController(UsuariosService usuarioService) { 
        this.usuarioService = usuarioService; 
    }
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody @Valid UsuarioDTO usuario) {
        UsuarioDTO usuarioDto = new UsuarioDTO();
        try {
            usuarioDto = usuarioService.encontrarPorLoguinESenhaDTO(usuario);
        } catch (Exception e) {
            return new ResponseEntity<>(usuarioDto, HttpStatus.BAD_GATEWAY);
        }
        return ResponseEntity.ok(usuarioDto);
    }
    
    @GetMapping 
    public List<UsuarioDTO> getAll() { 
        return usuarioService.findDTOAll(); 
    }
    
    @PostMapping 
    public UsuarioDTO create(@RequestBody @Valid UsuarioDTO usuario) { 
        return usuarioService.saveDTO(usuario); 
    }

    @PutMapping("/{id}")  
    public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuario) { 
        return usuarioService.updateDTO(id, usuario); 
    }
    
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable Long id) { 
        usuarioService.delete(id); 
    }
    
    @GetMapping("/{id}")
    public UsuarioDTO findDTOById(@PathVariable Long id){
        return usuarioService.findDTOById(id);
    }
    
    @PostMapping("/paginacao")
    public UsuarioPaginadosDTO getUsuariosPaginadosEOrdenadosPorQuery(@Valid @RequestBody RequestPageDTO dto) {
        return usuarioService.getUsuariosPaginadosEOrdenadosPorQuery(dto);
    }
}
