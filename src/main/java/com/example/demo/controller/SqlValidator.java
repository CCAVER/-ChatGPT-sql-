package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Component
public class SqlValidator {
    private final DataSource dataSource;
    @Autowired
    public SqlValidator(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean validateSql(String sql) {
        System.out.println(sql);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 语法校验通过
            return true;
        } catch (Exception e) {
            // 语法校验失败
            return false;
        }
    }
}
