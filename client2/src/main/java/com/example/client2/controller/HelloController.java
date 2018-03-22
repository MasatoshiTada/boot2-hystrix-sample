package com.example.client2.controller;

import com.example.client2.service.HelloService2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private final HelloService2 helloService;

    public HelloController(HelloService2 helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("helloDto", helloService.execute2("PREFIX ===== "));
        return "hello";
    }
}
