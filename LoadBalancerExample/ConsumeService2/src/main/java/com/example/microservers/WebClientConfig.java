package com.example.microservers;


import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(1000)
                .maxIdleTime(Duration.ofSeconds(1))
                .maxLifeTime(Duration.ofMinutes(5))
                .pendingAcquireMaxCount(5000)
                .pendingAcquireTimeout(Duration.ofSeconds(10))
                .metrics(true)
                .build();

        return WebClient.builder()
                .baseUrl("http://nginx-process")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create(provider)
                                .keepAlive(true)
                                .compress(true)
                                .option(ChannelOption.SO_KEEPALIVE, true)
                                .option(ChannelOption.TCP_NODELAY, true)
                ))
                .build();
    }
}
