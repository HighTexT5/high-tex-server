package com.uet.hightex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HightexApplication {

	public static void main(String[] args) {
		SpringApplication.run(HightexApplication.class, args);
	}

}
