package com.example.demo.process.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProcessRepository {
    private final JdbcTemplate jdbcTemplate;

    public void process() {
        jdbcTemplate.query(ProcessSql.process, (rs -> null));
    }
}
