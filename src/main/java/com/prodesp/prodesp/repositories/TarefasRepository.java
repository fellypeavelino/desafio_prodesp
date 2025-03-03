/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prodesp.prodesp.repositories;

import com.prodesp.prodesp.entities.Tarefas;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
    Optional<Tarefas> findById(Long id);
    
    @Query(
        "SELECT t FROM Tarefas t WHERE t.usuario.id = :usuarioId AND " +
        "LOWER(t.titulo) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(t.descricao) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(t.categoria.nome) LIKE LOWER(CONCAT('%', :term, '%')) " +
        "ORDER BY t.id DESC " 
    )
    Page<Tarefas> findPageByFiltro(@Param("term") String term, @Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query(
        "SELECT t FROM Tarefas t WHERE t.usuario.id = :usuarioId AND " +
        "LOWER(t.titulo) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(t.descricao) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(t.categoria.nome) LIKE LOWER(CONCAT('%', :term, '%')) " +
        "ORDER BY t.id DESC " 
    )
    List<Tarefas> findFiltro(@Param("term") String term, @Param("usuarioId") Long usuarioId);
    
    @Query("SELECT t FROM Tarefas t WHERE t.usuario.id = :usuarioId ORDER BY t.id DESC ")
    Page<Tarefas> findPage(Pageable pageable, @Param("usuarioId") Long usuarioId);
}
