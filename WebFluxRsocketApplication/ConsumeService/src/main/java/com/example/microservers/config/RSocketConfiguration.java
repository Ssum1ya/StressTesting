package com.example.microservers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RSocketConfiguration {
    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {

        return builder
                .rsocketConnector(connector ->
                        connector.reconnect(
                                Retry.fixedDelay(2, Duration.ofSeconds(2))
                        )
                )
                .dataMimeType(MediaType.APPLICATION_JSON)
                .tcp("app-process", 7000);
    }
}
