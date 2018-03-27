package net.no5no3.microservices.router.controller;

import net.no5no3.microservices.router.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private AccountClient accountClient;

    @GetMapping(value = "test")
    public String test(){
        return accountClient.byName();
    }
}
