/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.services.UsuariosService;
import java.util.List;
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
    private final UsuariosService service;
    public UsuariosController(UsuariosService service) { 
        this.service = service; 
    }
    
    @GetMapping 
    public List<Usuarios> getAll() { 
        return service.findAll(); 
    }
    
    @PostMapping 
    public Usuarios create(@RequestBody Usuarios usuario) { 
        return service.save(usuario); 
    }
}
