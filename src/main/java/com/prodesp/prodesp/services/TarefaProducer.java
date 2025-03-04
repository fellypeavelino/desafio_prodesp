/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.services;

import com.prodesp.prodesp.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class TarefaProducer {
    private static final String EXCHANGE_NAME = RabbitMQConfig.EXCHANGE_NAME;
    private static final String ROUTING_KEY = RabbitMQConfig.ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    public TarefaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mensagem);
    }
}
