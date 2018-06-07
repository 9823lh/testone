package com.example.disruptor.factory;

import com.example.disruptor.event.HelloEvent;
import com.lmax.disruptor.EventFactory;

public class HelloEventFactory implements EventFactory<HelloEvent> {

    @Override
    public HelloEvent newInstance() {
        return new HelloEvent();
    }
}
