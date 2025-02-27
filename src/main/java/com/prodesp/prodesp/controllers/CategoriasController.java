/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.CategoriasDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.services.CategoriasService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.val;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public CategoriasDTO create(@RequestBody CategoriasDTO categoria) { 
        return service.saveDTO(categoria); 
    }

    @PutMapping("/{id}") 
    public CategoriasDTO update(@PathVariable Long id, @RequestBody CategoriasDTO categoria) { 
        return service.updateDTO(id, categoria); 
    }
    
    @GetMapping("/{id}") 
    public CategoriasDTO findById(@PathVariable Long id) { 
        return service.findDTOById(id); 
    }

    @DeleteMapping("/{id}") 
    public void removeById(@PathVariable Long id) { 
        service.delete(id); 
    }    
}
