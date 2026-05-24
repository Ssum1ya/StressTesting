package com.example.microservers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProcessRepository {
    private final DatabaseClient databaseClient;

    public Mono<Void> process() {
        return databaseClient
                .sql(ProcessSql.process)
                .fetch()
                .rowsUpdated()
                .then();
    }
}
