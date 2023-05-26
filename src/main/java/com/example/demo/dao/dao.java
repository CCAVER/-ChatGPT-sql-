package com.example.demo.dao;

import com.example.demo.domain.DDL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface dao {
    @Select("show tables")
    List<String> getTabNames();
    @Select("show create table ${name}")
    DDL getDDLs(@Param("name") String tabName);
    @Select("select * from ${tab}")
    List<List<String>> getAll(@Param("tab") String tabName);
}
