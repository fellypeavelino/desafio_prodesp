/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.services.UsuariosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    /*@GetMapping 
    public List<Usuarios> getAll() { 
        return service.findAll(); 
    }
    
    @PostMapping 
    public Usuarios create(@RequestBody Usuarios usuario) { 
        return service.save(usuario); 
    }*/
}
