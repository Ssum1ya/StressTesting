package com.example.MainService;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StressTestUtil {

    public void stress(String pathToJs, String prometheusUrl) {
        ProcessBuilder pb = new ProcessBuilder(
                "k6", "run",
                pathToJs,
                "--out", "experimental-prometheus-rw"
        );

        pb.inheritIO();

        Map<String, String> env = pb.environment();
        env.put("K6_PROMETHEUS_RW_SERVER_URL", prometheusUrl);

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
