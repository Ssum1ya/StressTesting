package com.example.microservers.consume;

import com.example.microservers.consume.dto.ConsumeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConsumeController {
    private final ConsumeService consumeService;

    @PostMapping
    public Mono<ConsumeDTO> process(@RequestBody ConsumeDTO request) {
        log.info("request accepted");
        return consumeService.sendToProcess(request);
    }
}
