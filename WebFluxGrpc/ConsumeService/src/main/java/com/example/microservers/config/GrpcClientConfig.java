package com.example.microservers.config;

import com.example.grpc.ProcessServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcClientConfig {
    @Bean
    public ProcessServiceGrpc.ProcessServiceStub processServiceStub(GrpcChannelFactory channelFactory) {
        ManagedChannel channel = channelFactory.createChannel("process-service");
        return ProcessServiceGrpc.newStub(channel);
    }
}
