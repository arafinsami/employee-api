
package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@EnableFeignClients("com.employee")
@SpringBootApplication
public class ProxyApp {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApp.class, args);
	}
}


