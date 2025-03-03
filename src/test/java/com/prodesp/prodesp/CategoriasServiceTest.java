/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp;

import com.prodesp.prodesp.dtos.CategoriasDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.repositories.CategoriasRepository;
import com.prodesp.prodesp.services.CategoriasService;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Usuario
 */
class CategoriasServiceTest {

    @Mock
    private CategoriasRepository repository;
    
    @Mock
    private ConvertUtil convertUtil;
    
    @InjectMocks
    private CategoriasService categoriasService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoriasService = new CategoriasService(repository);
        categoriasService = spy(categoriasService);
        categoriasService.setConvertUtil(convertUtil);
    }
    
    @Test
    void testFindAll() {
        List<Categorias> categorias = Arrays.asList(new Categorias(1L, "Categoria 1", "#FFFFFF"));
        when(repository.findAll()).thenReturn(categorias);
        
        List<Categorias> result = categoriasService.findAll();
        
        assertEquals(1, result.size());
        assertEquals("Categoria 1", result.get(0).getNome());
        verify(repository, times(1)).findAll();
    }
    
    @Test
    void testFindDTOAll() {
        List<Categorias> categorias = Arrays.asList(new Categorias(1L, "Categoria 1", "#FFFFFF"));
        when(repository.findAllOrderedByIdDesc()).thenReturn(categorias);
        when(convertUtil.convertToDto(any(Categorias.class))).thenReturn(new CategoriasDTO(1L, "Categoria 1", "#FFFFFF"));
        
        List<CategoriasDTO> result = categoriasService.findDTOAll();
        
        assertEquals(1, result.size());
        assertEquals("Categoria 1", result.get(0).getNome());
        verify(repository, times(1)).findAllOrderedByIdDesc();
    }
    
    @Test
    void testSave() {
        Categorias categoria = new Categorias(1L, "Categoria 1", "#FFFFFF");
        when(repository.save(any(Categorias.class))).thenReturn(categoria);
        
        Categorias result = categoriasService.save(categoria);
        
        assertNotNull(result);
        assertEquals("Categoria 1", result.getNome());
        verify(repository, times(1)).save(categoria);
    }
    
    @Test
    void testSaveDTO() {
        CategoriasDTO dto = new CategoriasDTO(null, "Categoria 1", "#FFFFFF");
        Categorias categoria = new Categorias(1L, "Categoria 1", "#FFFFFF");
        when(repository.save(any(Categorias.class))).thenReturn(categoria);
        when(convertUtil.convertToDto(any(Categorias.class))).thenReturn(new CategoriasDTO(1L, "Categoria 1", "#FFFFFF"));
        
        CategoriasDTO result = categoriasService.saveDTO(dto);
        
        assertNotNull(result);
        assertEquals("Categoria 1", result.getNome());
        verify(repository, times(1)).save(any(Categorias.class));
    }
    
    @Test
    void testUpdateDTO() {
        CategoriasDTO dto = new CategoriasDTO(1L, "Categoria Atualizada", "#000000");
        Categorias categoria = new Categorias(1L, "Categoria Atualizada", "#000000");
        when(repository.save(any(Categorias.class))).thenReturn(categoria);
        when(convertUtil.convertToDto(any(Categorias.class))).thenReturn(dto);
        
        CategoriasDTO result = categoriasService.updateDTO(1L, dto);
        
        assertNotNull(result);
        assertEquals("Categoria Atualizada", result.getNome());
        verify(repository, times(1)).save(any(Categorias.class));
    }
    
    @Test
    void testFindDTOById() {
        Categorias categoria = new Categorias(1L, "Categoria 1", "#FFFFFF");
        when(repository.findById(1L)).thenReturn(Optional.of(categoria));
        when(convertUtil.convertToDto(any(Categorias.class))).thenReturn(new CategoriasDTO(1L, "Categoria 1", "#FFFFFF"));
        
        CategoriasDTO result = categoriasService.findDTOById(1L);
        
        assertNotNull(result);
        assertEquals("Categoria 1", result.getNome());
        verify(repository, times(1)).findById(1L);
    }
    
    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);
        
        categoriasService.delete(1L);
        
        verify(repository, times(1)).deleteById(1L);
    }
}

