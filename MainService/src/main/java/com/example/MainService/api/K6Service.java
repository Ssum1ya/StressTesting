package com.example.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class K6Service {
    private final StressTestUtil stressTestUtil;
    private final String prometheusUrl = "http://localhost:9090/api/v1/write";

    private final String pathToStressMvcHttpApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressMvcHttpApplication.js";
    private final String pathToStressWebFluxHttpApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressWebFluxHttpApplication.js";
    private final String pathToStressWebFluxRSocketApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressWebFluxRSocketApplication.js";
    private final String pathToStressMvcVirtual = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressMvcVirtualThreadsApplication.js";
    private final String pathToStressKubernetesApp = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/kubernetesApp.js";

    public void stressTest() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressMvcHttpApplication, prometheusUrl, "mvc-http");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressMvcVirtual, prometheusUrl, "mvc-virtual");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressWebFluxHttpApplication, prometheusUrl, "webflux-http");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressWebFluxRSocketApplication, prometheusUrl, "webflux-rsocket");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressKubernetesApp, prometheusUrl, "kubernetes-app");
        });

        executor.shutdown();
    }
}