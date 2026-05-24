package com.example.microservers.process;

import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @MessageMapping("process")
    public Mono<ProcessDTO> process(ProcessDTO request) {
        log.info("request accepted");
        return processService.process(request);
    }
}
