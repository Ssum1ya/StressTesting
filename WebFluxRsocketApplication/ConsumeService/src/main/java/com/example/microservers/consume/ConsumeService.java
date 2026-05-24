package com.example.microservers.consume;

import com.example.microservers.consume.dto.ConsumeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumeService {
    private final RSocketRequester rSocketRequester;

    public Mono<ConsumeDTO> sendToProcess(ConsumeDTO dto) {
        log.info("Go to process service on reactive socket protocol...");
        return rSocketRequester
                .route("process")
                .data(dto)
                .retrieveMono(ConsumeDTO.class);
    }
}
