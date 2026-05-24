package com.example.microservers;

import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    public ProcessDTO process(ProcessDTO request) {
        String processMessage = "Hello, " + request.message();
        ProcessDTO answer = new ProcessDTO(processMessage);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return answer;
    }
}
