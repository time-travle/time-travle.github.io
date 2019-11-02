package com.joven.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloWord {
    @Override
    @RequestMapping(value = "/sayhello")
    public String toString() {
        return "Hello Word!";
    }
}
