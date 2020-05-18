package ru.itis.dao.impl;

import ru.itis.dao.interfaces.MessagesRepository;
import ru.itis.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Long save(Message message) {
        entityManager.persist(message);
        return message.getId();
    }

    @Override
    public Optional<Message> find(Long id) {
        Message message = (Message) entityManager.createQuery("select message from Message message where message.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(message);
    }

    @Override
    public void update(Message message) {
        entityManager.merge(message);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Message message where message.id = :id")
                .setParameter("id", id);
    }

    @Override
    public Optional<Message> findByMessageId(String messageId) {
       Message message = (Message) entityManager.createQuery("select message from Message message where message.messageId = :messageId")
                .setParameter("messageId", messageId)
                .getSingleResult();
       return Optional.of(message);
    }
}
