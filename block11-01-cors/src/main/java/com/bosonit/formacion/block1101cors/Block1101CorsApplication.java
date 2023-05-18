package com.bosonit.formacion.block1101cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Block1101CorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block1101CorsApplication.class, args);
	}

}
