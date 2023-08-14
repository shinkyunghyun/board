package com.test.nameprojecet.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FirstApiProject {
    @GetMapping()
    public String hello(){
        return "hello world";
    }

}
