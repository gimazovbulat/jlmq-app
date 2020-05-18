package ru.itis.services.interfaces;


import ru.itis.dto.MessageDto;

public interface MessageService {
    MessageDto findByName(String name);

    MessageDto findById(Long id);

    void delete(Long id);

    void update(MessageDto messageDto);

    Long save(MessageDto messageDto);

    MessageDto findByMessageId(String genId);
}
