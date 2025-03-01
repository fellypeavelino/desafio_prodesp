/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prodesp.prodesp.repositories;

import com.prodesp.prodesp.entities.Usuarios;
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
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByLoguinAndSenha(String loguin, String senha);
    Optional<Usuarios> findById(Long id);
    
    @Query("SELECT u FROM Usuarios u WHERE " +
           "LOWER(u.loguin) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
           "LOWER(u.senha) LIKE LOWER(CONCAT('%', :term, '%')) ")
    Page<Usuarios> findPageByFiltro(@Param("term") String term, Pageable pageable);

    @Query("SELECT u FROM Usuarios u WHERE " +
            "LOWER(u.loguin) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(u.senha) LIKE LOWER(CONCAT('%', :term, '%')) ")
    List<Usuarios> findFiltro(@Param("term") String term);
    
    @Query("SELECT u FROM Usuarios u")
    Page<Usuarios> findPage(Pageable pageable);
}
