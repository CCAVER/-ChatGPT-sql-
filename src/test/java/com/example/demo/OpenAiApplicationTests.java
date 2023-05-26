package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.openai.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenAiApplicationTests {

    @Autowired
    private OpenAiApi openAiApi;
    @Test
    public void createChatCompletion2() {

        //Scanner in = new Scanner(System.in);
        //String input = in.next();
        System.out.println("开始");
        String input="你能生成sparkSQL吗";
        ChatMessage systemMessage = new ChatMessage("user", input);
        List<ChatMessage> messages=new ArrayList<ChatMessage>();
        //messages.add(new ChatMessage("user", "上周星期四，vivo50"));
        //messages.add(new ChatMessage("user", "今天星期四，vivo50"));
        messages.add(systemMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo-0301")
                .messages(messages)
                .user("testing")
                .max_tokens(500)
                .temperature(1.0)
                .build();
        //ExecuteRet executeRet = openAiApi.post(PathConstant.COMPLETIONS.CREATE_CHAT_COMPLETION, JSONObject.toJSONString(chatCompletionRequest), null);
        ExecuteRet executeRet = openAiApi.post(PathConstant.COMPLETIONS.CREATE_CHAT_COMPLETION, JSONObject.toJSONString(chatCompletionRequest), null);
        System.out.println("请求的结果是:"+executeRet);
        if(executeRet.getStatusCode()==-1){
            System.out.println("连接出错");
        }
        try{
        JSONObject result = JSONObject.parseObject(executeRet.getRespStr());
        List<ChatCompletionChoice> choices = result.getJSONArray("choices").toJavaList(ChatCompletionChoice.class);
        System.out.println(choices.get(0).getMessage().getContent());
        ChatMessage context = new ChatMessage(choices.get(0).getMessage().getRole(), choices.get(0).getMessage().getContent());
        System.out.println(context.getContent());
        }catch (Exception e){
            System.out.println("获取请求数据出错");
            e.printStackTrace();
        }
    }

}