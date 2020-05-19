package com.wch.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
	
	@Bean
	@Profile(value = {"test", "dev"})
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    }
	
}
