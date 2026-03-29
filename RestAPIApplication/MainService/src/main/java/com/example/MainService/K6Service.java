package com.example.MainService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class K6Service {

    public void stressTest() {
        ProcessBuilder pb = new ProcessBuilder(
                "k6", "run",
                "C:/Users/Proger/Desktop/ItSchoolProject/RestAPIApplication/MainService/src/scripts/stress.js",
                "--out", "experimental-prometheus-rw"
        );

        pb.inheritIO();

        Map<String, String> env = pb.environment();
        env.put("K6_PROMETHEUS_RW_SERVER_URL", "http://localhost:9090/api/v1/write");

        try {
            Process process = pb.start();

            boolean finished = process.waitFor(60, TimeUnit.SECONDS);

            if (finished) {
                System.out.println("✅ K6 тест завершён!");
            } else {
                System.out.println("⏰ K6 тест превысил 60 сек, останавливаем...");
                process.destroy();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
