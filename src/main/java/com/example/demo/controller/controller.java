package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.openai.*;
import com.example.demo.service.service;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class controller {
    @Autowired
    private OpenAiApi openAiApi;

    @Autowired
    private service serv;

    @RequestMapping("sql")
    public String createChatCompletion2(String msg,String lim, HttpSession session) {

        //Scanner in = new Scanner(System.in);
        //String input = in.next();
        System.out.println("开始");
        String input=msg;
        try {
            if(msg.equals("")||msg==null){return "失败,msg是："+msg+".";}
        }catch (Exception e){
            e.printStackTrace();
            return "失败,msg是："+msg+".";
        }
        ChatMessage systemMessage = new ChatMessage("user", input);
        List<ChatMessage> messages=new ArrayList<ChatMessage>();
        try{
            if(lim==null||lim.equals("")){
                for(String tabName:serv.getAllTab()){
                    String ddl=serv.getDDl(tabName);
                    messages.add(new ChatMessage("user",ddl));
                    System.out.println("the lim is null,added default");
                }
            }else {
                messages.add(new ChatMessage("user",lim));
                System.out.println("the lim is not null,added:"+lim);
            }
        }catch (Exception e){//获取DDL
            for(String tabName:serv.getAllTab()){
                String ddl=serv.getDDl(tabName);
                System.out.println(ddl);
                messages.add(new ChatMessage("user",ddl));
            }
        }
        messages.add(new ChatMessage("user","生成以‘;’号结尾的sql语句"));
        messages.add(systemMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .user("user")
                .max_tokens(500)
                .temperature(1.0)
                .build();
        ExecuteRet executeRet = openAiApi.post(PathConstant.COMPLETIONS.CREATE_CHAT_COMPLETION, JSONObject.toJSONString(chatCompletionRequest), null);
        System.out.println(executeRet);
        if(executeRet.getStatusCode()==-1){
            System.out.println("连接出错");
        }
        try{
            JSONObject result = JSONObject.parseObject(executeRet.getRespStr());
            List<ChatCompletionChoice> choices = result.getJSONArray("choices").toJavaList(ChatCompletionChoice.class);
            System.out.println(choices.get(0).getMessage().getContent());
            ChatMessage context = new ChatMessage(choices.get(0).getMessage().getRole(), choices.get(0).getMessage().getContent());
            System.out.println(context.getContent());
            //匹配是否是查询语句
            //String pattern = "^\\s*([a-zA-Z0-9]+)";//获取第一个单词的正则
            String pattern = "(?i)SELECT.*?;";//获取查询语句正则
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(context.getContent());
            //如果是查询语句则执行查询
//            boolean find=false;
//            while (m.find()){
//                find=!find;
//            if(m.group(0).equalsIgnoreCase("select")){
//            return context.getContent();
//                }else {//不是查询语句则反馈
//                return ("无法查询，原因是:"+context.getContent());
//                }
//            }
//            return "无法查询，尝试查询请使用："+context.getContent();

            boolean find=false;
            while (m.find()){
                find=!find;
                return (m.group(0));
            }
            if(!find){

            }
            return "无法查询，尝试查询请使用："+context.getContent();

        }catch (Exception e){
            System.out.println("获取请求数据出错");
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
