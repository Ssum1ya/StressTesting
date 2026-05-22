package com.example.microservers.process.domain;

public class ProcessSql {
    public static final String process = """
            select pg_sleep(2);
            """;
}
