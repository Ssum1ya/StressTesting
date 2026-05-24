package com.example.microservers;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProcessService {

    public Mono<ProcessDTO> process(ProcessDTO request) {
        return Mono.just(request.message())
                .map(msg -> "Hello, " + msg)
                .delayElement(Duration.ofSeconds(2))
                .map(ProcessDTO::new);
    }
}
