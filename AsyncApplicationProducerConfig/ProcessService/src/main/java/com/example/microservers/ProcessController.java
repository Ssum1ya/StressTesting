package com.example.microservers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public Mono<ProcessDTO> process(@RequestBody ProcessDTO request) {
        return processService.process(request);
    }
}
