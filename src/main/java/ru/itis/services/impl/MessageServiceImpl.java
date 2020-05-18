package ru.itis.services.impl;

import ru.itis.dao.interfaces.MessagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.interfaces.QueuesRepository;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.services.interfaces.MessageService;

import java.util.Optional;


@Service
public class MessageServiceImpl implements MessageService {
    private final MessagesRepository messagesRepository;
    private final QueuesRepository queuesRepository;

    public MessageServiceImpl(MessagesRepository messagesRepository, QueuesRepository queuesRepository) {
        this.messagesRepository = messagesRepository;
        this.queuesRepository = queuesRepository;
    }

    @Override
    public MessageDto findByName(String name) {
        return null;
    }

    @Override
    public MessageDto findById(Long id) {
        Optional<Message> optionalMessage = messagesRepository.find(id);
        if (optionalMessage.isPresent()) {
            return Message.toMessageDto(optionalMessage.get());
        }
        throw new IllegalStateException();
    }

    @Override
    public void delete(Long id) {

    }

    @Transactional
    @Override
    public void update(MessageDto messageDto) {
        messagesRepository.update(fromMessageDto(messageDto));
    }

    @Transactional
    @Override
    public Long save(MessageDto messageDto) {
        return messagesRepository.save(fromMessageDto(messageDto));
    }

    @Override
    public MessageDto findByMessageId(String messageId) {
        Optional<Message> optionalMessage = messagesRepository.findByMessageId(messageId);
        if (optionalMessage.isPresent()) {
            MessageDto messageDto = Message.toMessageDto(optionalMessage.get());
            System.out.println("findByMessageId " + messageDto);
            return messageDto;
        }
        throw new IllegalStateException();
    }


    private Message fromMessageDto(MessageDto message) {
        Message res =  Message.builder()
                .status(message.getStatus())
                .queue(queuesRepository.findByName(message.getQueueName()).get())
                .messageId(message.getMessageId())
                .id(message.getId())
                .body(message.getBody().toString())
                .build();
        System.out.println(res);
        return res;
    }
}
