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
import java.util.Optional;
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
        List<Categorias> categorias = repository.findAllOrderedByIdDesc(); 
        List<CategoriasDTO> categoriasDto = new ArrayList<>();
        categorias.forEach(c -> categoriasDto.add(convertUtil.convertToDto(c)));
        return categoriasDto;
    }
    
    public Categorias save(Categorias categoria) { 
        return repository.save(categoria); 
    }
    
    public CategoriasDTO saveDTO(CategoriasDTO categoriadto) { 
        Categorias categoria = new Categorias();
        categoria.setNome(categoriadto.getNome());
        categoria.setCor(categoriadto.getCor());
        categoria = save(categoria); 
        return convertUtil.convertToDto(categoria);
    }
    
    public CategoriasDTO updateDTO(Long id, CategoriasDTO categoriadto) { 
        Categorias categoria = new Categorias();
        categoria.setId(id);
        categoria.setNome(categoriadto.getNome());
        categoria.setCor(categoriadto.getCor());
        categoria = save(categoria); 
        return convertUtil.convertToDto(categoria);
    }
    
    public CategoriasDTO findDTOById(Long id){
        Optional<Categorias> op = repository.findById(id);
        CategoriasDTO dto = new CategoriasDTO();
        if(op.isPresent()){
            dto = convertUtil.convertToDto(op.get());
        }
        return dto;
    }
    
    public void delete(Long id){
        repository.deleteById(id);
    }
}
