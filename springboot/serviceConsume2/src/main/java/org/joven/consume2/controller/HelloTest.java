package org.joven.consume2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/consume2")
public class HelloTest {
    @Autowired
    private RestTemplate restTemplate;

    // 直接调用另一个的服务 不通过注册中心
    @RequestMapping(value = "/hello")
    public String hello() {
        String res = restTemplate.getForObject("http://localhost:8762/client1/hello ", String .class);
        return res;
    }
    // 通过注册中心 调用
    @RequestMapping(value = "/hello2")
    public String hello2() {
        String res = restTemplate.getForObject("http://serviceProvide1/client1/hello ", String .class);
        return res;
    }
}
