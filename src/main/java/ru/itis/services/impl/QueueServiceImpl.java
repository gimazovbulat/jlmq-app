package ru.itis.services.impl;

import ru.itis.dao.interfaces.QueuesRepository;
import ru.itis.dto.QueueDto;
import ru.itis.models.QueueModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.services.interfaces.QueueService;

import java.util.Optional;

@Transactional
@Service
public class QueueServiceImpl implements QueueService {
    private final QueuesRepository queuesRepository;

    public QueueServiceImpl(QueuesRepository queuesRepository) {
        this.queuesRepository = queuesRepository;
    }

    @Override
    public QueueDto findByName(String name) {
        Optional<QueueModel> optionalModel = queuesRepository.findByName(name);
        if (optionalModel.isPresent()){
            return QueueModel.toDto(optionalModel.get());
        }
        throw new IllegalStateException();
    }

    @Override
    public QueueDto findById(Long id) {
        Optional<QueueModel> optionalModel = queuesRepository.find(id);
        if (optionalModel.isPresent()){
            return QueueModel.toDto(optionalModel.get());
        }
        throw new IllegalStateException();

    }

    @Override
    public void delete(Long id) {
        queuesRepository.delete(id);
    }

    @Override
    public void update(QueueDto queueDto) {
        queuesRepository.update(QueueModel.fromDto(queueDto));
    }

    @Transactional
    @Override
    public Long save(QueueDto queueDto) {
        return queuesRepository.save(QueueModel.fromDto(queueDto));
    }
}
