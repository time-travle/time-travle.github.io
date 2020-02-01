package org.joven.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloWord {

    @RequestMapping(value = "/sayhello")
    public String sayhello() {
        return "Hello Word!";
    }
    @RequestMapping(value = "/sayhelloinhtml")
    public String sayHelloByHtml(){
        return "index";
    }
}
