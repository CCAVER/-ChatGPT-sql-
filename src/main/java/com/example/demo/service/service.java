package com.example.demo.service;

import com.example.demo.dao.dao;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service")
public class service {

    @Autowired
    dao dao0;

    public List<String> getAllTab(){
        return dao0.getTabNames();
    }
    public String getDDl(String name){
        return dao0.getDDLs(name).getCreate_table();
    }
}
