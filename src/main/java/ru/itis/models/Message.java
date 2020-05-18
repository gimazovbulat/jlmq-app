package ru.itis.models;

import ru.itis.dto.MessageDto;
import ru.itis.dto.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "jlmq_app", name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "queue_id", referencedColumnName = "id")
    private QueueModel queue;
    @Column(name = "body")
    private String body;
    @Column(name = "message_id")
    private String messageId;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    public static MessageDto toMessageDto(Message message) {
        return MessageDto.builder()
                .status(message.status)
                .queueName(message.getQueue().getName())
                .messageId(message.getMessageId())
                .id(message.getId())
                .body(message.getBody())
                .build();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", messageId='" + messageId + '\'' +
                ", status=" + status +
                ", queueName=" + queue.getName() +
                '}';
    }
}