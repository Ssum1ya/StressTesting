package com.example.MainService.api;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StressTestUtil {

    public void stress(String pathToJs, String prometheusUrl, String variant) {
        ProcessBuilder pb = new ProcessBuilder(
                "k6", "run",
                pathToJs,
                "--out", "experimental-prometheus-rw",
                "-e", "VARIANT=" + variant
        );

        pb.inheritIO();

        Map<String, String> env = pb.environment();
        env.put("K6_PROMETHEUS_RW_SERVER_URL", prometheusUrl);
        env.put("K6_PROMETHEUS_RW_PUSH_INTERVAL", "200ms");

        try {
            Process process = pb.start();

            boolean finished = process.waitFor(60, TimeUnit.SECONDS);

            if (finished) {
                System.out.println("✅ K6 тест завершён!");
                Thread.sleep(5000);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
