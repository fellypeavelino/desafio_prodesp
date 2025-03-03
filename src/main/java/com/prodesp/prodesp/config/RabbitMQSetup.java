/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.config;

/**
 *
 * @author Usuario
 */
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RabbitMQSetup {

    private static final String RABBITMQ_URL = "http://localhost:15672/api";
    private static final String AUTH = "Basic " + Base64.getEncoder().encodeToString("guest:guest".getBytes());

    private static void sendRequest(String endpoint, String method, String jsonBody) {
        try {
            URL url = new URL(RABBITMQ_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", AUTH);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            if (jsonBody != null) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Resposta do servidor (" + method + " " + endpoint + "): " + responseCode);

        } catch (Exception e) {
            System.err.println("Erro ao enviar requisição para " + endpoint + ": " + e.getMessage());
        }
    }

    public static void createExchangeAndQueue() {
        // Criar Exchange
        sendRequest("/exchanges/%2F/tarefa.exchange", "PUT", "{\"type\":\"direct\",\"durable\":true}");

        // Criar Fila
        sendRequest("/queues/%2F/tarefa.atrasada", "PUT", "{\"durable\":true}");

        // Bind da fila com a exchange
        sendRequest("/bindings/%2F/e/tarefa.exchange/q/tarefa.atrasada", "POST", "{\"routing_key\":\"tarefa.atrasada\"}");
    }
}

