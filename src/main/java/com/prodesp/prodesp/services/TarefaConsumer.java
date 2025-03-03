/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.config.NotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefaConsumer {
    
    @Autowired
    private NotificationHandler notificationHandler;
    
    private static final Logger logger = LoggerFactory.getLogger(TarefaConsumer.class);
    
    @RabbitListener(queues = "tarefa.atrasada.queue")
    public void processarMensagem(String mensagem) {

        logger.info("🔔 Notificação recebida: {}", mensagem);
        // Aqui você pode integrar com um sistema de e-mail, push notification, etc.
        
        notificationHandler.sendNotification("Tarefa pendente: " + mensagem);
    }
}
