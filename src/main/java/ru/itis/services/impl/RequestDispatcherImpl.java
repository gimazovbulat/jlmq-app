package ru.itis.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.MessageDto;
import ru.itis.dto.MessageStatus;
import ru.itis.dto.QueueDto;
import ru.itis.services.interfaces.MessageService;
import ru.itis.services.interfaces.QueueService;
import ru.itis.services.interfaces.RequestDispatcher;

import java.io.IOException;
import java.util.Map;

@Component
public class RequestDispatcherImpl implements RequestDispatcher {
    @Autowired
    JavaLabMessageQueue javaLabMessageQueue;
    @Autowired
    MessageService messageService;
    @Autowired
    QueueService queueService;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String dispatch(MessageDto message, WebSocketSession session) throws IOException {
        Map<String, String> headers = message.getHeaders();
        String command = headers.get("command");
        String res = "";

        switch (command) {
            case "send": {
                String queueName = headers.get("queue_name");
                MessageDto messageFromQueue = javaLabMessageQueue.putMessage(queueName, message);
                QueueDto queueDto = queueService.findByName(queueName);

                messageService.save(ru.itis.dto.MessageDto.builder()
                        .messageId(messageFromQueue.getMessageId())
                        .status(MessageStatus.valueOf(messageFromQueue.getStatus().getTitle()))
                        .queueName(queueDto.getName())
                        .body(message.getBody().toString())
                        .build());


                if (javaLabMessageQueue.getLastMessage(queueName) == null) {
                    MessageDto newMessage = javaLabMessageQueue.getNewMessage(session);
                    res = objectMapper.writeValueAsString(newMessage);
                }
                break;
            }
            case "subscribe": {
                String queueName = headers.get("queue_name");
                javaLabMessageQueue.subscribe(queueName, session);

                MessageDto newMessage = javaLabMessageQueue.getNewMessage(session);
                res = objectMapper.writeValueAsString(newMessage);
                break;
            }

            case "update": {
                MessageDto messageFromQueue = javaLabMessageQueue.update(message, session);

                ru.itis.dto.MessageDto foundMessage = messageService.findByMessageId(message.getHeaders().get("messageId"));
                foundMessage.setStatus(MessageStatus.valueOf(message.getBody().toString()));
                System.out.println("foundMessage " + foundMessage);
                messageService.update(foundMessage);

                if (messageFromQueue == null) {
                    messageFromQueue = javaLabMessageQueue.getNewMessage(session);
                }
                res = objectMapper.writeValueAsString(messageFromQueue);
                break;
            }
        }
        return res;
    }
}
