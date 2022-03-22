package com.irv.jms.gurujms.listener;

import com.irv.jms.gurujms.configuration.JmsConfig;
import com.irv.jms.gurujms.model.HelloMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    /**
     * es el consumidor
     * @param helloMessage se esta esprando un mensaje del tipo de este obj
     * @param headers headers
     * @param message aun no se
     */
    @JmsListener(destination = JmsConfig.MY_QUEUE_PERSONALIZADA)
    public void listen(@Payload HelloMessage helloMessage,//indica que se tiene que deseriazlizar esto
                       @Headers MessageHeaders headers,
                       Message message){
//        System.out.println("I got a message");
//        System.out.println(helloMessage);
    }
    @JmsListener(destination = JmsConfig.MY_QUEUE_PERSONALIZADA_RESPUESTA)
    public void listenForHello(@Payload HelloMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message) throws JMSException {

        HelloMessage payloadMsg = HelloMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

    }

}
