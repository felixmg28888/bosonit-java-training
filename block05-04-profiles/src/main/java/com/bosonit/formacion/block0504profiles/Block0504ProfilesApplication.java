package com.bosonit.formacion.block0504profiles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block0504ProfilesApplication implements CommandLineRunner {
	private final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(Block0504ProfilesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("activeProfile: {}. bd.url: {}", appConfig.getActiveProfile(), appConfig.getBdurl());
	}
}
