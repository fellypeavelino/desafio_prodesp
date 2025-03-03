/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.dtos.UsuarioPaginadosDTO;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.UsuariosRepository;
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
public class UsuariosService {
    @Autowired
    private ConvertUtil convertUtil;
    private final UsuariosRepository usuarioRepository;
    
    public UsuariosService(UsuariosRepository usuarioRepository) { 
        this.usuarioRepository = usuarioRepository; 
    }
    
    public List<Usuarios> findAll() { 
        return usuarioRepository.findAll(); 
    }

    public List<UsuarioDTO> findDTOAll() { 
        List<Usuarios> list = findAll();
        List<UsuarioDTO> listResult = new ArrayList<>();
        list.forEach(o -> listResult.add(getConvertUtil().convertToDto(o)));
        return listResult;
    }
    
    public Usuarios save(Usuarios usuario) { 
        return usuarioRepository.save(usuario); 
    }

    public UsuarioDTO saveDTO(UsuarioDTO usuarioDto) { 
        Usuarios usuario = new Usuarios();
        usuario.setLoguin(usuarioDto.getLoguin());
        usuario.setSenha(usuarioDto.getSenha());
        usuario = usuarioRepository.save(usuario); 
        return getConvertUtil().convertToDto(usuario);
    }
    
    public UsuarioDTO updateDTO(Long id, UsuarioDTO usuarioDto) {
        usuarioDto.setId(id);
        Usuarios usuario = getConvertUtil().convertToEntity(usuarioDto);
        usuario = usuarioRepository.save(usuario); 
        return getConvertUtil().convertToDto(usuario);
    }
    
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
    
    public Optional<Usuarios> encontrarPorLoguinESenha(String loguin, String senha) {
        return usuarioRepository.findByLoguinAndSenha(loguin, senha);
    }
    
    public UsuarioDTO encontrarPorLoguinESenhaDTO(UsuarioDTO dto) {
        Optional<Usuarios> op = this.encontrarPorLoguinESenha(dto.getLoguin(), dto.getSenha());
        if (op.isPresent()) {
            dto = getConvertUtil().convertToDto(op.get());
        }
        return dto;
    }
    
    public UsuarioDTO findDTOById(Long id){
        UsuarioDTO dto = new UsuarioDTO();
        Optional<Usuarios> op = usuarioRepository.findById(id);
        if (op.isPresent()) {
            dto = getConvertUtil().convertToDto(op.get());
        }
        return dto;
    }
    
    public UsuarioPaginadosDTO getUsuariosPaginadosEOrdenadosPorQuery(RequestPageDTO dto) {
        UsuarioPaginadosDTO result = new UsuarioPaginadosDTO();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        result.setParam(dto);

        String sortBy = dto.getSortBy();
        String sortDir = dto.getSortDir();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), sort);

        Page<Usuarios> page = null;
        long total = 0;
        if (dto.getFiltro() != null && !dto.getFiltro().isEmpty()) {
            page = usuarioRepository.findPageByFiltro(dto.getFiltro(), pageable);
            total = usuarioRepository.findFiltro(dto.getFiltro()).size();
        } else {
            page = usuarioRepository.findPage(pageable);
            total = usuarioRepository.count();
        }

        usuariosDTO = this.convertToListDto(page.getContent());
        result.setUsuarioDto(usuariosDTO);
        result.setTotal(total);
        return result;
    }
    
    private List<UsuarioDTO> convertToListDto(List<Usuarios> usuarios) {
        List<UsuarioDTO> listResult = new ArrayList<>();
        usuarios.forEach(c -> listResult.add(getConvertUtil().convertToDto(c)));
        return listResult;
    }

    /**
     * @return the convertUtil
     */
    public ConvertUtil getConvertUtil() {
        return convertUtil;
    }

    /**
     * @param convertUtil the convertUtil to set
     */
    public void setConvertUtil(ConvertUtil convertUtil) {
        this.convertUtil = convertUtil;
    }
}
