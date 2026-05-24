package com.example.microservers.domain;

public class ProcessSql {
    public static final String process = """
            SELECT pg_sleep(2)
            """;
}
