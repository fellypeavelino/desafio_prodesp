/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.dtos.CategoriasDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.repositories.CategoriasRepository;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class CategoriasService {
    
    @Autowired
    private ConvertUtil convertUtil;
    private final CategoriasRepository repository;
    
    public CategoriasService(CategoriasRepository repository) { 
        this.repository = repository; 
    }
    
    public List<Categorias> findAll() { 
        return repository.findAll(); 
    }
    
    public List<CategoriasDTO> findDTOAll() { 
        List<Categorias> categorias = repository.findAll(); 
        List<CategoriasDTO> categoriasDto = new ArrayList<>();
        categorias.forEach(c -> categoriasDto.add(convertUtil.convertToDto(c)));
        return categoriasDto;
    }
    
    public Categorias save(Categorias categoria) { 
        return repository.save(categoria); 
    }
}
