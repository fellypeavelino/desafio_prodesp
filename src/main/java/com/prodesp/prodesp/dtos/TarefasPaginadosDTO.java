/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.dtos;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class TarefasPaginadosDTO {
    private List<TarefasDTO> tarefasDto;
    private RequestPageDTO param;
    private Long total;
}
