/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.config.NotificationHandler;
import com.prodesp.prodesp.config.RabbitMQConfig;
import com.prodesp.prodesp.config.RabbitMQConfig;
import com.rabbitmq.client.AMQP.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
public class TarefaConsumer {
    
    @Autowired
    private NotificationHandler notificationHandler;
    
    private static final Logger logger = LoggerFactory.getLogger(TarefaConsumer.class);
    
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processarMensagem(@Payload String mensagem) {
        System.out.println("📩 Mensagem recebida: " + mensagem);
        logger.info("📩 Mensagem recebida: ", mensagem);
        // Aqui você pode integrar com um sistema de e-mail, push notification, etc.
        
        //notificationHandler.sendNotification("Tarefa pendente: " + mensagem);
    }

}
