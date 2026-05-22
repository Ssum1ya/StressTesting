package com.example.microservers.process;

import com.example.microservers.process.domain.ProcessRepository;
import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;

    public ProcessDTO process(ProcessDTO request) {
        processRepository.process();

        String processMessage = "Hello, " + request.message();
        return new ProcessDTO(processMessage);
    }
}
