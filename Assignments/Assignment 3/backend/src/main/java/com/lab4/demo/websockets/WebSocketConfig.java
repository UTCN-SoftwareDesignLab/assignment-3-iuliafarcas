package com.lab4.demo.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


import static com.lab4.demo.UrlMapping.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig {
    public void configureMessageBroker(MessageBrokerRegistry config)
    {
        config.enableSimpleBroker(CONSULTATION);
        //config.setApplicationDestinationPrefixes("/app");
    }

    public void registerStompEndpoints (StompEndpointRegistry registry)
    {
        registry.addEndpoint("/gs-guide-websocket")
                .withSockJS();
    }
}
