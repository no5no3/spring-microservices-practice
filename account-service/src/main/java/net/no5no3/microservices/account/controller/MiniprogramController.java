package net.no5no3.microservices.account.controller;

import net.no5no3.microservices.account.model.User;
import net.no5no3.microservices.account.service.AccountService;
import net.no5no3.microservices.account.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class MiniprogramController {
    private final static String API = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private AccountService accountService;
    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = "/authorize/miniprogram")
    public Map authorize(@RequestParam String c, HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("appid", "wx2f5b54e74a8f7809");
        params.put("secret", "f635cccb938fc793cdada40b8a142291");
        params.put("js_code", c);
        Map res = HttpUtil.httpGet(API, params);
        Map result = new HashMap();
        if (!request.getRemoteHost().startsWith("127.0.0.1") && res.containsKey("errcode")) {//error
            throw new RuntimeException(res.get("errcode").toString());
        } else {
            User user = accountService.miniprogramUser(res);
            ValueOperations<String, String> operations = template.opsForValue();
            String token = UUID.randomUUID().toString().replaceAll("[\\W]", "").toUpperCase();
            operations.set(token, String.format("%d-%s", user.getId(), res.get("session_key")), 2, TimeUnit.HOURS);
            result.put("token", token);
        }
        return result;
    }
}
