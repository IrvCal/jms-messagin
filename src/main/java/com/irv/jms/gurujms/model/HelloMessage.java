package com.irv.jms.gurujms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//tiene que tener Serializable si no, no va a jalar
public class HelloMessage  implements Serializable {
    private UUID id;
    private String message;
}
