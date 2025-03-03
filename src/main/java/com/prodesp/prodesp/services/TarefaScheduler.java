/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.repositories.TarefasRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefaScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(TarefaScheduler.class);
    
    private final TarefasRepository tarefaRepository;
    private final TarefaProducer tarefaProducer;

    public TarefaScheduler(TarefasRepository tarefaRepository, TarefaProducer tarefaProducer) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaProducer = tarefaProducer;
    }

    //@Scheduled(fixedRate = 3600000) // Executa a cada 1 hora (3600000 ms)
    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void verificarTarefasPendentes() {
        logger.info("Verificando tarefas pendentes...");
        
        LocalDateTime umDiaAtras = LocalDateTime.now().minusDays(1);
        List<Tarefas> tarefasAtrasadas = tarefaRepository.findByCompletadaAndDataBefore(false, umDiaAtras);

        for (Tarefas tarefa : tarefasAtrasadas) {
            String mensagem = "A tarefa '" + tarefa.getTitulo() + "' está atrasada!";
            tarefaProducer.enviarMensagem(mensagem);
            logger.info("Notificação enviada para tarefa atrasada: {}", tarefa.getTitulo());
        }
    }
}
