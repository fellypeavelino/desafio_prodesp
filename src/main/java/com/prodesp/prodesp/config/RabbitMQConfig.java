/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Usuario
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "tarefa.atrasada";
    public static final String EXCHANGE_NAME = "amq.direct";
    //public static final String EXCHANGE_NAME = "tarefa.exchange";
    public static final String ROUTING_KEY = "tarefa.atrasada";
    
    @Bean
    public DirectExchange tarefaExchange() {
        log.info("tarefaExchange");
        
        return new DirectExchange(EXCHANGE_NAME)
            ;
    }

    @Bean
    public Queue queue() {
        log.info("queue");
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        log.info("binding");
        return BindingBuilder.bind(queue()).to(tarefaExchange()).with(ROUTING_KEY);
    }
    
   @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
    
}

