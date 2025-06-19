package com.hong.demo.secu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @GetMapping("/hello")
    public String helloA() {
        return "Hello Admin!";
    }

}
