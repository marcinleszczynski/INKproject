package com.project.articles.service.impl;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.project.articles.service.AiService;

@Service
public class OpenAiService implements AiService {

    private final ChatClient chatClient;

    public OpenAiService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @Override
    public String answer(String text) {
        var message = """
                give me the name of the city of US, that this article is about. If article applies to broader region than 1 city, then answer GLOBAL. If you are sure about the state, but don't know the city then also answer GLOBAL. Give me only city name with state as 2 letters, or GLOBAL:

                {article}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("article", text));
        Prompt prompt = promptTemplate.create();

        String response = chatClient.prompt().user(prompt.getContents()).call().content();
        String answer = response.strip();
        return answer;
    }
    
}
