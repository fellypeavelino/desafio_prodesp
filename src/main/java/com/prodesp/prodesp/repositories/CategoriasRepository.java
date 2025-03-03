/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prodesp.prodesp.repositories;

import com.prodesp.prodesp.entities.Categorias;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Usuario
 */
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    Optional<Categorias> findById(Long id);
    
    @Query("SELECT c FROM Categorias c ORDER BY c.id DESC")
    List<Categorias> findAllOrderedByIdDesc();
}
