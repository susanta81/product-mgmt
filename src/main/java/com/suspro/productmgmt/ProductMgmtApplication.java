package com.suspro.productmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMgmtApplication.class, args);
	}
	
	
	
}
