/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.TarefasDTO;
import com.prodesp.prodesp.dtos.TarefasPaginadosDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.TarefasRepository;
import com.prodesp.prodesp.services.TarefasService;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Usuario
 */
@ExtendWith(MockitoExtension.class)
public class TarefasServiceTest {

    @Mock
    private TarefasRepository tarefasRepository;

    @Mock
    private ConvertUtil convertUtil;

    @InjectMocks
    private TarefasService tarefasService;
    
    TarefasDTO tarefaDTO;
    Tarefas tarefa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tarefasService = new TarefasService(tarefasRepository);
        tarefasService = spy(tarefasService);
        tarefasService.setConvertUtil(convertUtil);
        
        tarefaDTO = new TarefasDTO();
        tarefaDTO.setUsuario_id(1L);
        tarefaDTO.setCategoria_id(2L);

        tarefa = new Tarefas();
        Usuarios usuario = new Usuarios();
        usuario.setId(1L);
        tarefa.setUsuario(usuario);

        Categorias categoria = new Categorias();
        categoria.setId(2L);
        tarefa.setCategoria(categoria);
    }
    
    @Test
    void testFindAll() {
        List<Tarefas> tarefasList = List.of(new Tarefas());
        when(tarefasRepository.findAll()).thenReturn(tarefasList);

        List<Tarefas> result = tarefasService.findAll();

        assertEquals(1, result.size());
        verify(tarefasRepository, times(1)).findAll();
    }

    @Test
    void testFindDTOAll() {
        Tarefas tarefa = new Tarefas();
        TarefasDTO tarefaDTO = new TarefasDTO();

        when(tarefasRepository.findAll()).thenReturn(List.of(tarefa));
        when(convertUtil.convertToDto(any(Tarefas.class))).thenReturn(tarefaDTO);

        List<TarefasDTO> result = tarefasService.findDTOAll();

        assertEquals(1, result.size());
        verify(tarefasRepository, times(1)).findAll();
        verify(convertUtil, times(1)).convertToDto(any(Tarefas.class));
    }

    @Test
    void testSave() {
        Tarefas tarefa = new Tarefas();
        when(tarefasRepository.save(any(Tarefas.class))).thenReturn(tarefa);

        Tarefas result = tarefasService.save(tarefa);

        assertNotNull(result);
        verify(tarefasRepository, times(1)).save(any(Tarefas.class));
    }

    @Test
    void testSaveDTO() {

        when(convertUtil.convertToEntity(any(TarefasDTO.class))).thenReturn(tarefa);
        when(tarefasRepository.save(any(Tarefas.class))).thenReturn(tarefa);

        TarefasDTO result = tarefasService.saveDTO(tarefaDTO);

        assertNotNull(result);
        assertEquals(tarefaDTO.getUsuario_id(), result.getUsuario_id());
        assertEquals(tarefaDTO.getCategoria_id(), result.getCategoria_id());

        verify(convertUtil, times(1)).convertToEntity(any(TarefasDTO.class));
        verify(tarefasRepository, times(1)).save(any(Tarefas.class));
    }

    @Test
    void testDelete() {
        doNothing().when(tarefasRepository).deleteById(1L);

        tarefasService.delete(1L);

        verify(tarefasRepository, times(1)).deleteById(1L);
    }
    
    @Test
    void testUpdateDTO() {
        when(convertUtil.convertToEntity(any(TarefasDTO.class))).thenReturn(tarefa);
        when(tarefasRepository.save(any(Tarefas.class))).thenReturn(tarefa);
        
        TarefasDTO result = tarefasService.updateDTO(1L, tarefaDTO);
        assertNotNull(result);
    }
  
    
    @Test
    void testFindDTOById() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(convertUtil.convertToDto(any(Tarefas.class))).thenReturn(tarefaDTO);
        
        TarefasDTO result = tarefasService.findDTOById(1L);
        assertNotNull(result);
    }
    
    @Test
    void testGetTarefasPaginadosEOrdenadosPorQuery() {
        RequestPageDTO requestPageDTO = new RequestPageDTO();
        requestPageDTO.setPage(0);
        requestPageDTO.setSize(10);
        requestPageDTO.setSortBy("id");
        requestPageDTO.setSortDir("ASC");
        
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        Page<Tarefas> page = new PageImpl<>(Arrays.asList(tarefa));
        
        when(tarefasRepository.findPage(pageable, 1L)).thenReturn(page);
        when(tarefasRepository.totalTarefasNaoCompletadas(1L)).thenReturn(1L);
        when(convertUtil.convertToDto(any(Tarefas.class))).thenReturn(tarefaDTO);
        
        TarefasPaginadosDTO result = tarefasService.getTarefasPaginadosEOrdenadosPorQuery(requestPageDTO, 1L);
        assertNotNull(result);
        assertEquals(1, result.getTarefasDto().size());
    }
}

