package com.hong.demo.secu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String helloU() {
        return "Hello User!";
    }

}
