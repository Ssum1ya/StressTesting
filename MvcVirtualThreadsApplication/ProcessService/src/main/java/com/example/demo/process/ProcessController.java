package com.example.demo.process;

import com.example.demo.process.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
@Slf4j
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<ProcessDTO> process(@RequestBody ProcessDTO request) {
        log.info("request accepted");
        ProcessDTO answer = processService.process(request);

        return ResponseEntity.ok(answer);
    }
}
