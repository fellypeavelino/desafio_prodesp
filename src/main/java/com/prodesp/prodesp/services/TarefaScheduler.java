/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.entities.Tarefas;
import com.prodesp.prodesp.repositories.TarefasRepository;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
        this.executeCurl();
        LocalDateTime umDiaAtras = LocalDateTime.now().minusDays(1);
        List<Tarefas> tarefasAtrasadas = tarefaRepository.findByCompletadaAndDataBefore(false, umDiaAtras);

        for (Tarefas tarefa : tarefasAtrasadas) {
            String mensagem = "A tarefa '" + tarefa.getTitulo() + "' está atrasada!";
            tarefaProducer.enviarMensagem(mensagem);
            logger.info("Notificação enviada para tarefa atrasada: {}", tarefa.getTitulo());
        }
    }
    
    public void executeCurl() {
        try {
            String urlString = "http://localhost:15672/api/exchanges/%2F/tarefa.exchange";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString("guest:guest".getBytes()));
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonBody = "{\"type\":\"direct\",\"durable\":true}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Resposta do servidor: " + responseCode);
        } catch (Exception e) {
            System.err.println("Erro ao criar exchange: " + e.getMessage());
        }
    }
}
