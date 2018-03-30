package net.no5no3.microservices.english.controller;

import com.github.pagehelper.PageHelper;
import net.no5no3.microservices.english.mapper.VocabularyMapper;
import net.no5no3.microservices.english.model.Vocabulary;
import net.no5no3.microservices.english.service.VocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
//@RequestMapping(value = "/h",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@CacheConfig(cacheNames = "haha")
public class VocabularyController {
    @Autowired
    private DiscoveryClient discoveryClient;
    //    @Autowired
//    private EurekaClient eurekaClient;
    @Autowired
    private VocabularyMapper vocabularyMapper;
    @Autowired
    private VocaService vocaService;

    @GetMapping(value = "/vo/{num}")
//    @Cacheable
//    @HystrixCommand(fallbackMethod = "fallback")
    public String list(@Validated @Pattern(regexp = "[1-9]+") @PathVariable("num") @ModelAttribute("num") Integer num, @RequestParam(value = "size", required = false) Integer size, HttpSession httpSession) {
        List<ServiceInstance> list = discoveryClient.getInstances("account");
        list.forEach(i -> {
            System.out.println(i.getUri());
        });
        return httpSession == null ? "fuck" : httpSession.getId();
    }

    //    public String fallback(Integer num,Integer size,Optional<String> haha){
////        throw new RuntimeException("fuck fallback");
////        System.out.println("fuck fallback");
//        return "fuck fallback by account";
//    }
    @GetMapping(value = "/test")
    public String test() {
        return "test";
//        return eurekaClient.test();
    }

    @GetMapping(value = "/vocabulary/{letter}")
    public Vocabulary voca(@PathVariable("letter") String letter, HttpSession httpSession) {
        return Optional.of(vocabularyMapper.selectByLetter(letter)).orElse(new Vocabulary());
    }

    @PutMapping(value = "/vocabulary", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public int addVoca(@RequestBody List<Vocabulary> vocabulary) {
        return vocaService.add(vocabulary);
    }

    @PostMapping(value = "/vocabulary", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public int updateVoca(@RequestBody Vocabulary vocabulary) {
        return vocabularyMapper.update(vocabulary);
    }

    @DeleteMapping(value = "/vocabulary/{id}")
    public int del(@PathVariable("id") Integer id) {
        return vocabularyMapper.delete(id);
    }

    @GetMapping(value = "/vocabulary")
    public List<Vocabulary> groupVoca(@RequestParam("g") String g, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        return vocabularyMapper.groupByClassify(g);
    }

    @GetMapping(value = "/vocabulary/allgroup")
    public List<String> group() {
        return vocabularyMapper.group();
    }
}
