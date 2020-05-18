package ru.itis.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.services.interfaces.RequestDispatcher;

import java.io.IOException;

@Component("simpleHandler")
public class SimpleHandler extends TextWebSocketHandler {
    @Autowired
    RequestDispatcher requestDispatcher;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        System.out.println("--------------------------------");
        System.out.println("SESSION " + session);
        System.out.println("--------------------------------");
        String payload = textMessage.getPayload();
        ru.itis.MessageDto message = objectMapper.readValue(payload, ru.itis.MessageDto.class);
        System.out.println("handleTextMessage " + message);
        String resText = requestDispatcher.dispatch(message, session);
        if (!resText.isEmpty()){
            session.sendMessage(new TextMessage(resText));
        }
    }

}
