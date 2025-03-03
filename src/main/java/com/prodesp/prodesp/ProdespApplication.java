package com.prodesp.prodesp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@EnableRabbit
@SpringBootApplication
public class ProdespApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdespApplication.class, args);
	}

}
