package com.example.microservers.process;

import com.example.grpc.ProcessProto;
import com.example.grpc.ProcessServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ProcessGrpcServer extends ProcessServiceGrpc.ProcessServiceImplBase {
    private final ProcessService processService;

    @Override
    public void process(
            ProcessProto.ProcessRequest request,
            StreamObserver<ProcessProto.ProcessResponse> responseObserver
    ) {
        log.info("request accepted");
        processService.process(request.getMessage())
                .map(message -> ProcessProto.ProcessResponse.newBuilder()
                        .setMessage(message)
                        .build())
                .subscribe(
                        responseObserver::onNext,
                        responseObserver::onError,
                        responseObserver::onCompleted
                );
    }
}
