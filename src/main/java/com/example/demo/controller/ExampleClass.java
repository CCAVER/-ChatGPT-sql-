package com.example.demo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class ExampleClass {
    private final JdbcTemplate jdbcTemplate;

    public ExampleClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean validateSQL(String sql) {
        try {
            jdbcTemplate.execute(con -> con.prepareStatement(sql),
                    (PreparedStatementCallback<Void>) preparedStatement -> null);
            System.out.println(sql);
            return true; // SQL语句有效
        } catch (Exception e) {
            return false; // SQL语句无效
        }
    }
}
