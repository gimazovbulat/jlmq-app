package ru.itis.services.interfaces;


import ru.itis.dto.QueueDto;

public interface QueueService {
    QueueDto findByName(String name);
    QueueDto findById(Long id);
    void delete(Long id);
    void update(QueueDto queueDto);
    Long save(QueueDto queueDto);
}
