package net.no5no3.microservices.account.service;

import net.no5no3.microservices.account.mapper.UserMapper;
import net.no5no3.microservices.account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public User miniprogramUser(Map res) {
        User user = userMapper.selectByOpenid((String) res.get("openid"));
        if (user == null) {
            user = new User();
            user.setName((String) res.get("openid"));
            user.setOpenid((String) res.get("openid"));
            user.setUnionid((String) res.get("unionid"));
            user.setPassword((String) res.get("session_key"));
            userMapper.insert(user);
        }//end
        return user;
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.selectByName(name);
    }
}
