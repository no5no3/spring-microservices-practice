package net.no5no3.microservices.router.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account")
public interface AccountClient {
    @GetMapping(value="/user")
    String byName();
}
