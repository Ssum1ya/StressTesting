package com.example.microservers.process;

import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @MessageMapping("process")
    public Mono<ProcessDTO> process(ProcessDTO request) {
        return processService.process(request);
    }
}
