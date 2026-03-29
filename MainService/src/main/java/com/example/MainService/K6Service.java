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

    private final String pathToJsRestApiApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressRestApiApplication.js";
    private final String prometheusUrlRestApiApplication = "http://localhost:9090/api/v1/write";

    private final String pathToJsAsyncApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressAsyncApplication.js";
    private final String prometheusUrlAsyncApplication = "http://localhost:8085/api/v1/write";

    private final String pathToJsAsyncConfigApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressAsyncConfigPoolApplication.js";
    private final String prometheusUrlAsyncConfigApplication = "http://localhost:8089/api/v1/write";

    private final String pathToJsAsyncApplicationProducerConfig = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressAsyncApplicationProducerConfig.js";
    private final String prometheusUrlAsyncApplicationProducerConfig = "http://localhost:8093/api/v1/write";

    private final String pathToJsLoadBalancer = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressLoadBalancer.js";
    private final String prometheusUrlLoadBalancer = "http://localhost:8095/api/v1/write";

    public void stressTest() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            stressTestUtil.stress(pathToJsRestApiApplication, prometheusUrlRestApiApplication);
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToJsAsyncApplication, prometheusUrlAsyncApplication);
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToJsAsyncConfigApplication, prometheusUrlAsyncConfigApplication);
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToJsAsyncApplicationProducerConfig, prometheusUrlAsyncApplicationProducerConfig);
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToJsLoadBalancer, prometheusUrlLoadBalancer);
        });

        executor.shutdown();
    }
}