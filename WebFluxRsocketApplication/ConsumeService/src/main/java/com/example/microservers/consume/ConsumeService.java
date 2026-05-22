package com.example.microservers.consume;

import com.example.microservers.consume.dto.ConsumeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConsumeService {
    private final RSocketRequester rSocketRequester;

    public Mono<ConsumeDTO> sendToProcess(ConsumeDTO dto) {
        return rSocketRequester
                .route("process")
                .data(dto)
                .retrieveMono(ConsumeDTO.class);
    }
}
