package com.example.demo;

import com.example.demo.controller.ExampleClass;
import com.example.demo.controller.SqlValidator;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        String ddl = "CREATE TABLE `user` (\n" +
                "  `uid` int NOT NULL,\n" +
                "  `username` varchar(255) DEFAULT NULL,\n" +
                "  `password` varchar(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`uid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";
        try {
            System.out.println(ddl);
            CCJSqlParserUtil.parse(ddl);
            System.out.println("YES");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("NO");
        }
    }

}
