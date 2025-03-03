/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp;

import com.prodesp.prodesp.dtos.RequestPageDTO;
import com.prodesp.prodesp.dtos.UsuarioDTO;
import com.prodesp.prodesp.dtos.UsuarioPaginadosDTO;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.UsuariosRepository;
import com.prodesp.prodesp.services.UsuariosService;
import com.prodesp.prodesp.utils.ConvertUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Usuario
 */

class UsuariosServiceTest {
        
    @Mock
    private UsuariosRepository usuarioRepository;

    @Mock
    private ConvertUtil convertUtil;

    @InjectMocks
    private UsuariosService usuariosService;

    private Usuarios usuario;
    private UsuarioDTO usuarioDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuariosService = new UsuariosService(usuarioRepository);
        usuariosService = spy(usuariosService);
        usuariosService.setConvertUtil(convertUtil);
        
        usuario = new Usuarios();
        usuario.setId(1L);
        usuario.setLoguin("user1");
        usuario.setSenha("password");

        usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setLoguin("user1");
        usuarioDto.setSenha("password");
    }

    @Test
    void testFindAll() {
        List<Usuarios> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuarios> result = usuariosService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getLoguin());
    }

    @Test
    void testFindDTOAll() {
        List<Usuarios> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        List<UsuarioDTO> result = usuariosService.findDTOAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getLoguin());
    }

    @Test
    void testSave() {
        when(usuarioRepository.save(any(Usuarios.class))).thenReturn(usuario);

        Usuarios result = usuariosService.save(usuario);

        assertNotNull(result);
        assertEquals("user1", result.getLoguin());
    }

    @Test
    void testSaveDTO() {
        when(usuarioRepository.save(any(Usuarios.class))).thenReturn(usuario);
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        UsuarioDTO result = usuariosService.saveDTO(usuarioDto);

        assertNotNull(result);
        assertEquals("user1", result.getLoguin());
    }

    @Test
    void testUpdateDTO() {
        when(convertUtil.convertToEntity(any(UsuarioDTO.class))).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuarios.class))).thenReturn(usuario);
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        UsuarioDTO result = usuariosService.updateDTO(1L, usuarioDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("user1", result.getLoguin());
    }

    @Test
    void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> usuariosService.delete(1L));

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEncontrarPorLoguinESenha() {
        when(usuarioRepository.findByLoguinAndSenha("user1", "password")).thenReturn(Optional.of(usuario));

        Optional<Usuarios> result = usuariosService.encontrarPorLoguinESenha("user1", "password");

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getLoguin());
    }

    @Test
    void testEncontrarPorLoguinESenhaDTO() {
        when(usuarioRepository.findByLoguinAndSenha("user1", "password")).thenReturn(Optional.of(usuario));
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        UsuarioDTO result = usuariosService.encontrarPorLoguinESenhaDTO(usuarioDto);

        assertNotNull(result);
        assertEquals("user1", result.getLoguin());
    }

    @Test
    void testFindDTOById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        UsuarioDTO result = usuariosService.findDTOById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("user1", result.getLoguin());
    }

    @Test
    void testGetUsuariosPaginadosEOrdenadosPorQuery() {
        RequestPageDTO pageDTO = new RequestPageDTO();
        pageDTO.setPage(0);
        pageDTO.setSize(10);
        pageDTO.setSortBy("id");
        pageDTO.setSortDir("ASC");

        List<Usuarios> usuariosList = Collections.singletonList(usuario);
        Page<Usuarios> page = new PageImpl<>(usuariosList);
        
        when(usuarioRepository.findPage(any(Pageable.class))).thenReturn(page);
        when(usuarioRepository.count()).thenReturn(1L);
        when(convertUtil.convertToDto(any(Usuarios.class))).thenReturn(usuarioDto);

        UsuarioPaginadosDTO result = usuariosService.getUsuariosPaginadosEOrdenadosPorQuery(pageDTO);

        assertNotNull(result);
        assertEquals(1, result.getUsuarioDto().size());
        assertEquals(1L, result.getTotal());
    }
}
