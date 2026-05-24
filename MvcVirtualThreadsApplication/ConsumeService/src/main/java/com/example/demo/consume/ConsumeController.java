package com.example.demo.consume;

import com.example.demo.consume.dto.ConsumeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConsumeController {
    private final ConsumeService consumeService;

    @PostMapping
    public ResponseEntity<String> process(@RequestBody ConsumeDTO request) {
        log.info("Request accepted");
        String answer = consumeService.sendToProcess(request);

        return ResponseEntity.ok(answer);
    }
}