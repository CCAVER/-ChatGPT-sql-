package com.example.demo.domain;

public class DDL {
    public String Table;
    public String Create_table;

    public DDL(String table, String create_table) {
        Table = table;
        Create_table = create_table;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public String getCreate_table() {
        return Create_table;
    }

    public void setCreate_table(String create_table) {
        Create_table = create_table;
    }
}
