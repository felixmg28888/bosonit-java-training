package com.bosonit.formacion.block1602springappticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class Block1602SpringAppTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block1602SpringAppTicketApplication.class, args);
	}

}
