package com.example.microservers.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketConfiguration {
    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder)
    {
        return args -> {
            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);
        };
    }
}
