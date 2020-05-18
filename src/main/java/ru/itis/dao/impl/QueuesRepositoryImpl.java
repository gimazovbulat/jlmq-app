package ru.itis.dao.impl;

import ru.itis.dao.interfaces.QueuesRepository;
import ru.itis.models.QueueModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class QueuesRepositoryImpl implements QueuesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(QueueModel queue) {
        entityManager.persist(queue);
        return queue.getId();
    }

    @Override
    public Optional<QueueModel> find(Long id) {
        QueueModel queueModel = (QueueModel) entityManager
                .createQuery("select queue from QueueModel queue where queue.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(queueModel);
    }

    @Override
    public void update(QueueModel queue) {
        entityManager.merge(queue);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<QueueModel> findByName(String name) {
        QueueModel queueModel = (QueueModel) entityManager.createQuery("select queue from QueueModel queue where queue.name = :name")
                .setParameter("name", name)
                .getSingleResult();
        return Optional.of(queueModel);
    }
}
