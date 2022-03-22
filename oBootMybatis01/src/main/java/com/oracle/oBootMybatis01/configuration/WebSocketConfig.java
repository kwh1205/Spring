package com.oracle.oBootMybatis01.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.oracle.oBootMybatis01.handler.SocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Autowired
	SocketHandler socketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) { //웹소켓핸들러를 registry에 넣음
		registry.addHandler(socketHandler, "/chating");//chating라고 뷰딴에서 id에설정하면 핸들러에서 처리
	}

}
