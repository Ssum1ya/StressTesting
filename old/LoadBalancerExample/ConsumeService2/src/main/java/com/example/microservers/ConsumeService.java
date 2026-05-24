package com.example.microservers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ConsumeService {
    private final WebClient webClient;

    public Mono<ConsumeDTO> sendToProcess(ConsumeDTO dto) {
        return webClient.post()
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ConsumeDTO.class)
                .timeout(Duration.ofSeconds(10));
    }
}
