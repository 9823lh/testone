package com.example.disruptor.event;

import lombok.Data;

@Data
public class HelloEvent {
    private Integer id;
    private String value;
}
