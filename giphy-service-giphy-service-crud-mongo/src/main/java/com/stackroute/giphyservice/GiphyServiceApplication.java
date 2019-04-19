package com.stackroute.giphyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GiphyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiphyServiceApplication.class, args);
	}

}

