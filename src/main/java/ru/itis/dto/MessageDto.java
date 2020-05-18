package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private Map<String, String> headers;
    private Object body;
    private MessageStatus status;
    private String queueName;
    private String messageId;

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", headers=" + headers +
                ", body=" + body +
                ", status=" + status +
                ", queueName=" + queueName +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
