package com.example.microservers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConsumeController {
    private final ConsumeService consumeService;

    @PostMapping
    public CompletableFuture<ResponseEntity<ConsumeDTO>> process(@RequestBody ConsumeDTO request) {
        return CompletableFuture.supplyAsync(() ->
                ResponseEntity.ok(new ConsumeDTO(consumeService.sendToProcess(request))));
    }
}
