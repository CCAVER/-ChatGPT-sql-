package com.example.demo;
import java.util.regex.*;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.openai.*;
import com.example.demo.service.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testDB {

    @Autowired
    private service serv;
    @Test
    public void createChatCompletion2() {
        for(String tabName:serv.getAllTab()){
            String ddl=serv.getDDl(tabName);
            System.out.println(ddl);
        }
        String pattern = "(?i)SELECT.*?;";//获取第一个单词的正则
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("SELECT * from sb;");
        boolean find=false;
        //如果是查询语句则执行查询
        while (m.find()){
        if(m.group(0).equalsIgnoreCase("select")){
            System.out.println("yes"+m.group(0));
        }else {
            System.out.println("no"+m.group(0));
        }
        find=true;
        break;
        }
        if(!find){System.out.println("not found");}



    }

}