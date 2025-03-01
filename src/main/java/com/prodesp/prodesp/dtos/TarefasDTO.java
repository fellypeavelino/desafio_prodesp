/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class TarefasDTO {
    
    private Long id;
    @NotNull(message = "O campo 'titulo' não pode ser nulo.")
    private String titulo;
    private String descicao;
    @NotNull(message = "O campo 'completada' não pode ser nulo.")
    private boolean completada;
    private LocalDateTime data;
    @NotNull(message = "O campo 'usuario_id' não pode ser nulo.")
    private Long usuario_id;
    @NotNull(message = "O campo 'categoria_id' não pode ser nulo.")
    private Long categoria_id; 
}
