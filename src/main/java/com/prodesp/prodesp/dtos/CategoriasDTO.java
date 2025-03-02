/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class CategoriasDTO {
    private Long id;
    @NotNull(message = "O campo 'nome' não pode ser nulo.")
    private String nome;
    @NotNull(message = "O campo 'cor' não pode ser nulo.")
    private String cor;
}
