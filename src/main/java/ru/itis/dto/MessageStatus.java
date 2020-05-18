package ru.itis.dto;

import lombok.Getter;
import lombok.Setter;

public enum MessageStatus {
    NEW(0, "NEW"), ACCEPTED(1, "ACCEPTED"), COMPLETED(2, "COMPLETED");

    @Getter
    @Setter
    private String title;
    private int num;

    MessageStatus(int num, String title) {
        this.num = num;
        this.title = title;
    }
}