/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.TarefasDTO;
import com.prodesp.prodesp.dtos.TarefasPaginadosDTO;
import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.services.TarefasService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/tarefas")
public class TarefasController {
    private final TarefasService service;
    public TarefasController(TarefasService service) { 
        this.service = service; 
    }
    
    @GetMapping 
    public List<TarefasDTO> getAll() { 
        return service.findDTOAll(); 
    }
    
    @PostMapping 
    public TarefasDTO create(@RequestBody @Valid TarefasDTO tarefaDto) { 
        return service.saveDTO(tarefaDto); 
    }

    @PutMapping("/{id}")  
    public TarefasDTO update(@PathVariable Long id, @RequestBody @Valid TarefasDTO tarefaDto) { 
        return service.updateDTO(id, tarefaDto); 
    }
    
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable Long id) { 
        service.delete(id); 
    }
    
    @GetMapping("/{id}")
    public TarefasDTO findDTOById(@PathVariable Long id){
        return service.findDTOById(id);
    }
    
    @PostMapping("/paginacao")
    public TarefasPaginadosDTO getTarefasPaginadosEOrdenadosPorQuery(@Valid @RequestBody RequestPageDTO dto) {
        return service.getTarefasPaginadosEOrdenadosPorQuery(dto);
    }
}
