package com.example.microservers.process;

import com.example.microservers.domain.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;

    public Mono<String> process(String message) {
        return processRepository.process()
                .then(Mono.just(message))
                .map(msg -> "Hello, " + msg);
    }
}
