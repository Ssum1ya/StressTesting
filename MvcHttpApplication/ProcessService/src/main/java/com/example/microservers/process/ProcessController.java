package com.example.microservers.process;

import com.example.microservers.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<ProcessDTO> process(@RequestBody ProcessDTO request) {
        ProcessDTO answer = processService.process(request);

        return ResponseEntity.ok(answer);
    }
}
