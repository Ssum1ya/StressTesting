package com.example.microservers.consume;

import com.example.grpc.ProcessProto;
import com.example.grpc.ProcessServiceGrpc;
import com.example.microservers.consume.dto.ConsumeDTO;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConsumeService {

    private final ProcessServiceGrpc.ProcessServiceStub stub;

    public Mono<ConsumeDTO> sendToProcess(ConsumeDTO dto) {
        return Mono.create(sink -> {
            stub.process(
                    ProcessProto.ProcessRequest.newBuilder()
                            .setMessage(dto.message())
                            .build(),
                    new StreamObserver<>() {
                        @Override
                        public void onNext(ProcessProto.ProcessResponse response) {
                            sink.success(new ConsumeDTO(response.getMessage()));
                        }

                        @Override
                        public void onError(Throwable t) {
                            sink.error(t);
                        }

                        @Override
                        public void onCompleted() {}
                    }
            );
        });
    }
}
