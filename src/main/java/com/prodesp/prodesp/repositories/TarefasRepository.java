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
    
    @Query("SELECT t FROM Tarefas t WHERE " +
           "LOWER(t.titulo) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
           "LOWER(t.descicao) LIKE LOWER(CONCAT('%', :term, '%')) ")
    Page<Tarefas> findPageByFiltro(@Param("term") String term, Pageable pageable);

    @Query("SELECT t FROM Tarefas t WHERE " +
            "LOWER(t.titulo) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(t.titulo) LIKE LOWER(CONCAT('%', :term, '%')) ")
    List<Tarefas> findFiltro(@Param("term") String term);
    
    @Query("SELECT t FROM Tarefas t")
    Page<Tarefas> findPage(Pageable pageable);
}
