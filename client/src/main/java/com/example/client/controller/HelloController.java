package com.example.client.controller;

import com.example.client.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("helloDto", helloService.execute("PREFIX ===== "));
        return "hello";
    }
}
