package com.social.notificaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificaServiceApplication.class, args);
	}

}
