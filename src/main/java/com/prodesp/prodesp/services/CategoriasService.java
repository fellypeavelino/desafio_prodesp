/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.repositories.CategoriasRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class CategoriasService {
    private final CategoriasRepository repository;
    public CategoriasService(CategoriasRepository repository) { 
        this.repository = repository; 
    }
    
    public List<Categorias> findAll() { 
        return repository.findAll(); 
    }
    
    public Categorias save(Categorias categoria) { 
        return repository.save(categoria); 
    }
}
