package ru.itis.models;

import ru.itis.dto.QueueDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "jlmq_app", name = "queue")
public class QueueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "queue", fetch = FetchType.EAGER)
    private List<Message> messages;

    public static QueueModel fromDto(QueueDto queueDto) {
        return QueueModel.builder()
                .name(queueDto.getName())
//                .messages(queueDto.getMessages().stream().map(Message::fromMessageDto).collect(Collectors.toList()))
                .id(queueDto.getId())
                .build();
    }

    public static QueueDto toDto(QueueModel queueModel) {
        return QueueDto.builder()
                .id(queueModel.getId())
                .messages(queueModel.getMessages().stream().map(Message::toMessageDto).collect(Collectors.toList()))
                .name(queueModel.getName())
                .build();
    }
}
