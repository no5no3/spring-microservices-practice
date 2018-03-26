package net.no5no3.microservices.account.controller;

import net.no5no3.microservices.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
//@RequestMapping(value = "/h",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@CacheConfig(cacheNames = "haha")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
