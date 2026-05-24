package com.example.demo.process.domain;

public class ProcessSql {
    public static final String process = """
            select pg_sleep(2);
            """;
}
