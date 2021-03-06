package net.no5no3.microservices.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import java.io.IOException;

@EnableEurekaServer
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AccountApplication.class, args);
	}
}
