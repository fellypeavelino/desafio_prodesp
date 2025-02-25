/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.services.TarefasService;
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
@RequestMapping("/tarefas")
public class TarefasController {
    private final TarefasService service;
    public TarefasController(TarefasService service) { 
        this.service = service; 
    }
    
    @GetMapping 
    public List<Tarefas> getAll() { 
        return service.findAll(); 
    }
    
    @PostMapping 
    public Tarefas create(@RequestBody Tarefas tarefa) { 
        return service.save(tarefa); 
    }
}
