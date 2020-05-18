package ru.itis.dao.interfaces;


import ru.itis.models.Message;

import java.util.Optional;

public interface MessagesRepository extends CrudRepository<Long, Message> {
    Optional<Message> findByMessageId(String genId);
}
