package com.cavoli.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerRunner {
	public static void main(String[] args) {
		SpringApplication.run(com.cavoli.eureka.EurekaServerRunner.class, args);
		System.out.println("Entering Eureka server");
	}
}
