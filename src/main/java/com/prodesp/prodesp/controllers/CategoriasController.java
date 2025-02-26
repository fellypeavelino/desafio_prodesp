/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.CategoriasDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.services.CategoriasService;
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
@RequestMapping("/categorias")
public class CategoriasController {
    private final CategoriasService service;
    public CategoriasController(CategoriasService service) { 
        this.service = service; 
    }
    
    @GetMapping 
    public List<CategoriasDTO> getAll() { 
        return service.findDTOAll(); 
    }
    
    @PostMapping 
    public Categorias create(@RequestBody Categorias categoria) { 
        return service.save(categoria); 
    }
}
