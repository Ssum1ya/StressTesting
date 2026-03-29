package com.example.microservers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ConsumeService {
    private final RestClient userServiceRestClient;

    public String sendToProcess(ConsumeDTO dto) {
        ConsumeDTO answer = userServiceRestClient.post()
                .uri("/process")
                .body(dto)
                .retrieve()
                .body(ConsumeDTO.class);

        return answer.message();
    }
}
