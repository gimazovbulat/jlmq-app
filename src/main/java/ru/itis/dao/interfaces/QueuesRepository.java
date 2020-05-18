package ru.itis.dao.interfaces;


import ru.itis.models.QueueModel;

import java.util.Optional;

public interface QueuesRepository extends CrudRepository<Long, QueueModel> {
    Optional<QueueModel> findByName(String name);
}
