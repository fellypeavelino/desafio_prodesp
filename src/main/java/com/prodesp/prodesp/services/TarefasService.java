/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.TarefasDTO;
import com.prodesp.prodesp.dtos.TarefasPaginadosDTO;
import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.TarefasRepository;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefasService {
    @Autowired
    private ConvertUtil convertUtil;
    private final TarefasRepository repository;
    public TarefasService(TarefasRepository repository) { 
        this.repository = repository; 
    }
    
    public List<Tarefas> findAll() { 
        return repository.findAll(); 
    }
    
    public List<TarefasDTO> findDTOAll() { 
        List<Tarefas> list = findAll();
        List<TarefasDTO> listResult = new ArrayList<>();
        list.forEach(o -> listResult.add(convertUtil.convertToDto(o)));
        return listResult;
    }
    
    public Tarefas save(Tarefas tarefa) { 
        return repository.save(tarefa); 
    }
    
    public TarefasDTO saveDTO(TarefasDTO tarefaDto) { 
        Tarefas tarefa = convertUtil.convertToEntity(tarefaDto);
        
        Usuarios usuario = new Usuarios();
        usuario.setId(tarefaDto.getUsuario_id());
        tarefa.setUsuario(usuario);
        
        Categorias categoria = new Categorias();
        categoria.setId(tarefaDto.getCategoria_id());
        tarefa.setCategoria(categoria);
        
        tarefa = repository.save(tarefa); 
        tarefaDto.setId(tarefa.getId());
        return tarefaDto;
    }
    
    public TarefasDTO updateDTO(Long id, TarefasDTO tarefaDto) {
        tarefaDto.setId(id); 
        return saveDTO(tarefaDto);
    }
    
    public void delete(Long id){
        repository.deleteById(id);
    }
    
    public TarefasDTO findDTOById(Long id){
        TarefasDTO dto = new TarefasDTO();
        Optional<Tarefas> op = repository.findById(id);
        if (op.isPresent()) {
            dto = convertUtil.convertToDto(op.get());
            Long categoria_id = op.get().getCategoria().getId();
            dto.setCategoria_id(categoria_id);
        }
        return dto;
    }
    
    public TarefasPaginadosDTO getTarefasPaginadosEOrdenadosPorQuery(RequestPageDTO dto, Long usuario_id) {
        TarefasPaginadosDTO result = new TarefasPaginadosDTO();
        List<TarefasDTO> tarefasDTO = new ArrayList<>();
        result.setParam(dto);
        
        String sortBy = dto.getSortBy();
        String sortDir = dto.getSortDir();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), sort);

        Page<Tarefas> page = null;
        long total = 0;
        if (dto.getFiltro() != null && !dto.getFiltro().isEmpty()) {
            page = repository.findPageByFiltro(dto.getFiltro(), usuario_id, pageable);
            total = repository.findFiltro(dto.getFiltro(), usuario_id).size();
        } else {
            page = repository.findPage(pageable, usuario_id);
            total = repository.totalTarefasNaoCompletadas(usuario_id);
        }
        List<Tarefas> listaTarefas = page.getContent();
        for (Tarefas ltaTarefa : listaTarefas) {
            TarefasDTO tarefaDto = convertUtil.convertToDto(ltaTarefa);
            Long categori_id = ltaTarefa.getCategoria().getId();
            tarefaDto.setCategoria_id(categori_id);
            tarefasDTO.add(tarefaDto);
        }
        //tarefasDTO = this.convertToListDto(page.getContent());
        result.setTarefasDto(tarefasDTO);
        result.setTotal(total);
        return result;
    }
    
    private List<TarefasDTO> convertToListDto(List<Tarefas> tarefas) {
        List<TarefasDTO> listResult = new ArrayList<>();
        tarefas.forEach(c -> listResult.add(convertUtil.convertToDto(c)));
        return listResult;
    }
}
