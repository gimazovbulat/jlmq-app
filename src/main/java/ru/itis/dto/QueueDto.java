package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QueueDto {
    private Long id;
    private String name;
    private List<MessageDto> messages;

    public QueueDto(String name) {
        this.name = name;
    }
}
