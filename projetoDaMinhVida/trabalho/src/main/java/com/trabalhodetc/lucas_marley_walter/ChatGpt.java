package com.trabalhodetc.lucas_marley_walter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import io.reactivex.Flowable;

public class ChatGpt {
    private static final String apiKey = "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";
    private String path;
    String response =  "";
    StringBuilder buffer = new StringBuilder();
    public void setPath(String path){
        this.path = path;
    }
    public void request(String request) {

        OpenAiService service = new OpenAiService(apiKey);

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(
                ChatMessageRole.SYSTEM.value(), "o usuario vai descrever um automato e voce vai cria-lo e escrever um arquivo jff deve ser criado com jflap 6.4, não dê mais nenhuma informação adicional apenas o arquivo");
        final ChatMessage secondMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),"exemplo: crie uma afn com alfabeto 0 e 1 que sempre termina em 1. a resposta deve ser:<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--Created with JFLAP 6.4.--><structure>&#13;<type>fa</type>&#13;<automaton>&#13;<!--The list of states.-->&#13;<state id=\"0\" name=\"q0\">&#13;<x>92.0</x>&#13;<y>200.0</y>&#13;<initial/>&#13;</state>&#13;<state id=\"1\" name=\"q1\">&#13;<x>234.0</x>&#13;<y>194.0</y>&#13;<final/>&#13;</state>&#13;<!--The list of transitions.-->&#13;<transition>&#13;<from>0</from>&#13;<to>0</to>&#13;<read>0</read>&#13;</transition>&#13;<transition>&#13;<from>0</from>&#13;<to>0</to>&#13;<read>1</read>&#13;</transition>&#13;<transition>&#13;<from>0</from>&#13;<to>1</to>&#13;<read>1</read>&#13;</transition>&#13;</automaton>&#13;</structure>");
        final ChatMessage userMessage = new ChatMessage("user", request);

        messages.add(systemMessage);
        messages.add(secondMessage);
        messages.add(userMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(10000)
                .logitBias(new HashMap<>())
                .build();
                
        
            
        Flowable<ChatCompletionChunk> flowableResult = service.streamChatCompletion(chatCompletionRequest).doOnError(Throwable::printStackTrace);
         // Create a StringBuilder object to store the result
        //Essa porra é assincrona. Como se fosse o setBlocking(false) no socket
        flowableResult.subscribe(chunk -> {
            chunk.getChoices().forEach(choice -> {
                String result = choice.getMessage().getContent();
                if (result != null) {   
                    buffer.append(result);
                    System.out.print(result);                    
                }
            });
        }, Throwable::printStackTrace, () -> System.out.println());
        
        service.shutdownExecutor();
            
       

        // Subscribe to the Flowable object and print the result        
        
    }

}
