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
//    private final String prometheusUrlRestApiApplication = "http://localhost:9090/api/v1/write";

    private final String pathToStressWebFluxHttpApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressWebFluxHttpApplication.js";
//    private final String prometheusUrlWebFlux = "http://localhost:8089/api/v1/write";

    private final String pathToStressWebFluxRSocketApplication = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressWebFluxRSocketApplication.js";
//    private final String prometheusUrlFuture = "http://localhost:8085/api/v1/write";

//    private final String pathToStressProducerWebFlux = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressProducerWebFlux.js";
//    private final String prometheusUrlProducerWebFlux = "http://localhost:8093/api/v1/write";
//
//    private final String pathToStressNginxLoadBalancer = "C:/Users/Proger/Desktop/ItSchoolProject/MainService/src/scripts/stressNginxLoadBalancer.js";
//    private final String prometheusUrlNginxLoadBalancer = "http://localhost:8095/api/v1/write";

    public void stressTest() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressMvcHttpApplication, prometheusUrl, "mvc-http");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressWebFluxHttpApplication, prometheusUrl, "webflux-http");
        });

        executor.submit(() -> {
            stressTestUtil.stress(pathToStressWebFluxRSocketApplication, prometheusUrl, "webflux-rsocket");
        });

//        executor.submit(() -> {
//            stressTestUtil.stress(pathToStressProducerWebFlux, prometheusUrlProducerWebFlux);
//        });
//
//        executor.submit(() -> {
//            stressTestUtil.stress(pathToStressNginxLoadBalancer, prometheusUrlNginxLoadBalancer);
//        });

        executor.shutdown();
    }
}