/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.UsuariosRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class UsuariosService {
    private final UsuariosRepository repository;
    public UsuariosService(UsuariosRepository repository) { 
        this.repository = repository; 
    }
    
    public List<Usuarios> findAll() { 
        return repository.findAll(); 
    }
    
    public Usuarios save(Usuarios usuario) { 
        return repository.save(usuario); 
    }
}
