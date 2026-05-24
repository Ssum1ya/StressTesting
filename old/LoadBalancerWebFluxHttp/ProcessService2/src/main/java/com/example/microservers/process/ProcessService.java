package com.example.microservers.process;

import com.example.microservers.domain.ProcessRepository;
import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;

    public Mono<ProcessDTO> process(ProcessDTO request) {
        return processRepository.process()
                .then(Mono.just(request.message()))
                .map(msg -> "Hello, " + msg)
                .map(ProcessDTO::new);
    }
}
