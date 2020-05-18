package ru.itis.handlers;

import lombok.SneakyThrows;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Map;

@Component
public class HandshakeHandlerImpl implements HandshakeHandler {

    private final DefaultHandshakeHandler defaultHandshakeHandler;

    public HandshakeHandlerImpl() {
        this.defaultHandshakeHandler = new DefaultHandshakeHandler();
    }

    @SneakyThrows
    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        System.out.println("handshake");
        return defaultHandshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
    }
}
