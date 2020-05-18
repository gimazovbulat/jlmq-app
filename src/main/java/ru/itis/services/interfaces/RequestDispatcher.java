package ru.itis.services.interfaces;

import org.springframework.web.socket.WebSocketSession;
import ru.itis.MessageDto;

import java.io.IOException;

public interface RequestDispatcher {
    String dispatch(MessageDto message, WebSocketSession session) throws IOException;
}
