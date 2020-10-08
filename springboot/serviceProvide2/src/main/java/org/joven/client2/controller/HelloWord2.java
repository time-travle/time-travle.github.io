package org.joven.client2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client2")
public class HelloWord2 {
    @RequestMapping(value = "/hello")
    public String hello() {
        return "this is ClientApplication2 ,hi!";
    }
}
