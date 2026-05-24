package com.example.MainService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/k6")
@RequiredArgsConstructor
public class K6Controller {
    private final K6Service k6Service;

    @PostMapping
    public void stressTest() {
        k6Service.stressTest();
    }
}
