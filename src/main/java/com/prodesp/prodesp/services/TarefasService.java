/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.repositories.TarefasRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefasService {
    private final TarefasRepository repository;
    public TarefasService(TarefasRepository repository) { 
        this.repository = repository; 
    }
    
    public List<Tarefas> findAll() { 
        return repository.findAll(); 
    }
    
    public Tarefas save(Tarefas tarefa) { 
        return repository.save(tarefa); 
    }
}
