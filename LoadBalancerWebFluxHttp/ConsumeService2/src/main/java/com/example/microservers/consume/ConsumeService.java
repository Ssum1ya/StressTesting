package com.example.microservers.consume;

import com.example.microservers.consume.dto.ConsumeDTO;
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
                .uri("/process")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ConsumeDTO.class);
    }
}
