package com.example.microservers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {
    private static final Logger log = LoggerFactory.getLogger(ProcessController.class);
    private final ProcessService processService;

    @PostMapping
    public Mono<ProcessDTO> process(@RequestBody ProcessDTO request) {
        log.info("request processed by 2");
        return processService.process(request);
    }
}
