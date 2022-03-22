package com.irv.jms.gurujms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irv.jms.gurujms.configuration.JmsConfig;
import com.irv.jms.gurujms.model.HelloMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jsmTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 20000)
    public void sendMessage(){
        System.out.println("Sending message");

        HelloMessage message= HelloMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello world primer mensaje")
                .build();
        jsmTemplate.convertAndSend(JmsConfig.MY_QUEUE_PERSONALIZADA,message);

        System.out.println("Message sended");
    }
    @Scheduled(fixedRate = 20000)
    public void sendAndRecibe() throws JMSException {
        System.out.println("Sending message");

        HelloMessage message= HelloMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();
        Message messageReceived =
        jsmTemplate.sendAndReceive(JmsConfig.MY_QUEUE_PERSONALIZADA_RESPUESTA, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;
                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type","com.irv.jms.gurujms.model.HelloMessage");
                    return  helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("Algo salio man en sendAndRecibe");
                }
            }
        });
        System.out.println(messageReceived.getBody(String.class));
    }
}
