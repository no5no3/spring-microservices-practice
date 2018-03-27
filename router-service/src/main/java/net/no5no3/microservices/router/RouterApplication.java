package net.no5no3.microservices.router;

import net.no5no3.microservices.router.filter.AuthorizeZuulPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@EnableEurekaServer
@EnableCircuitBreaker
@EnableFeignClients
@EnableZuulProxy
@SpringBootApplication
public class RouterApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RouterApplication.class, args);
	}
	@Bean
	AuthorizeZuulPreFilter authorizeZuulPreFilter() {
		return new AuthorizeZuulPreFilter();
	}
}
