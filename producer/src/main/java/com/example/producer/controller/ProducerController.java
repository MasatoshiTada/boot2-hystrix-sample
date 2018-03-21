package com.example.producer.controller;

import com.example.producer.dto.HelloDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class ProducerController {

    private final int errorRate;
    private final boolean errorEnabled;
    private final int sleepSeconds;
    private final boolean sleepEnabled;
    private final Random random;

    public ProducerController(@Value("${error.rate}") int errorRate,
                              @Value("${error.enabled}") boolean errorEnabled,
                              @Value("${sleep.seconds}") int sleepSeconds,
                              @Value("${sleep.enabled}")boolean sleepEnabled) {
        this.errorRate = errorRate;
        this.errorEnabled = errorEnabled;
        this.sleepSeconds = sleepSeconds;
        this.sleepEnabled = sleepEnabled;
        this.random = new Random();
    }

    @GetMapping("/hello")
    public HelloDto hello() {
        if (errorEnabled) {
            throwException();
        }
        if (sleepEnabled) {
            sleep();
        }
        return new HelloDto("Hello!!");
    }

    private void throwException() {
        if (random.nextInt(errorRate) == 0) {
            throw new RuntimeException("ERROR IN PRODUCER!!!!!");
        }
    }

    private void sleep() {
        try {
            Thread.sleep(sleepSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
