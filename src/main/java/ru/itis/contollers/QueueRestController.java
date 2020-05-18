package ru.itis.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.QueueCreationDto;
import ru.itis.dto.QueueDto;
import ru.itis.services.impl.JavaLabMessageQueue;
import ru.itis.services.interfaces.QueueService;

@RestController
public class QueueRestController {
    private final JavaLabMessageQueue javaLabMessageQueue;
    private final QueueService queueService;

    public QueueRestController(JavaLabMessageQueue javaLabMessageQueue, QueueService queueService) {
        this.javaLabMessageQueue = javaLabMessageQueue;
        this.queueService = queueService;
    }

    @PostMapping("/api/queue")
    public ResponseEntity createQueue(@RequestBody QueueCreationDto queueCreationDto) {
        javaLabMessageQueue.createQueue(queueCreationDto.getName());
        Long queueId = queueService.save(new QueueDto(queueCreationDto.getName()));
        return ResponseEntity.ok().build();
    }
}
