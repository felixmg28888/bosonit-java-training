package com.bosonit.formacion.block0702crudvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Block0702CrudValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block0702CrudValidationApplication.class, args);
	}

}
