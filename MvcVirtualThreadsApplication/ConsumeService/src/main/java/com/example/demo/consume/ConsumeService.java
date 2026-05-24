package com.example.demo.consume;

import com.example.demo.consume.dto.ConsumeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumeService {
    private final RestClient userServiceRestClient;

    public String sendToProcess(ConsumeDTO dto) {
        log.info("Go to process service...");
        ConsumeDTO answer = userServiceRestClient.post()
                .uri("/process")
                .body(dto)
                .retrieve()
                .body(ConsumeDTO.class);
        log.info("OK");
        return answer.message();
    }
}
