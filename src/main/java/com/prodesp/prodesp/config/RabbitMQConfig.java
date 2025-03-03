/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Usuario
 */
import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "tarefa.exchange";
    public static final String QUEUE_NAME = "tarefa.queue";
    public static final String ROUTING_KEY = "tarefa.routingKey";

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME).build(); // Garante que a fila seja persistente
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
