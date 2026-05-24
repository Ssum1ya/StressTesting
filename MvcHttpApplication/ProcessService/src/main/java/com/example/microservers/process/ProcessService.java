package com.example.microservers.process;

import com.example.microservers.process.domain.ProcessRepository;
import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;

    public ProcessDTO process(ProcessDTO request) {
        log.info("Go to db...");
        processRepository.process();

        String processMessage = "Hello, " + request.message();
        log.info("OK");
        return new ProcessDTO(processMessage);
    }
}
