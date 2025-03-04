/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.config.NotificationHandler;
import com.prodesp.prodesp.config.RabbitMQConfig;
import com.prodesp.prodesp.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefaConsumer {
    
//    @Autowired
//    private NotificationHandler notificationHandler;
//    
    private static final Logger logger = LoggerFactory.getLogger(TarefaConsumer.class);
    
    @RabbitListener(bindings = @QueueBinding(value = @Queue(RabbitMQConfig.QUEUE_NAME),
            exchange = @Exchange(name = RabbitMQConfig.EXCHANGE_NAME),
            key = RabbitMQConfig.ROUTING_KEY))
    public void processarMensagem(@Payload String mensagem) {
        System.out.println("📩 Mensagem recebida: " + mensagem);
        logger.info("📩 Mensagem recebida: ", mensagem);
//        // Aqui você pode integrar com um sistema de e-mail, push notification, etc.
//        
//        notificationHandler.sendNotification("Tarefa pendente: " + mensagem);
    }
}
