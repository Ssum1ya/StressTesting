package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/check-threads")
    public String checkThreads() {
        Thread current = Thread.currentThread();
        return "isVirtual: " + current.isVirtual() +
                ", name: " + current.getName();
    }
}
