package com.irv.jms.gurujms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration//no olvidar poner esta anotacion porque si no no se logra pasar de String a OBJ
public class JmsConfig {

    public static final String MY_QUEUE_PERSONALIZADA = "my-hello-world";
    public static final String MY_QUEUE_PERSONALIZADA_RESPUESTA = "hello-world_respuesta";

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
