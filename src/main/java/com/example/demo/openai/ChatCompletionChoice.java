package com.example.demo.openai;

import lombok.Data;

@Data
public class ChatCompletionChoice {

    Integer index;

    ChatMessage message;

    String finishReason;
}