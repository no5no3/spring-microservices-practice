package net.no5no3.microservices.account.service;

import net.no5no3.microservices.account.model.User;

import java.util.Map;

public interface AccountService {
    User miniprogramUser(Map res);

    User findUserByName(String name);
}
