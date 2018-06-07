package com.example.disruptor.handler;

import com.example.disruptor.event.HelloEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author lvhua
 */
public class HelloEventHandler implements EventHandler<HelloEvent> {
    @Override
    public void onEvent(HelloEvent helloEvent, long l, boolean b) throws Exception {
        System.out.println("-----id is ["+helloEvent.getId()+"] value is ["+helloEvent.getValue()+"]");
    }
}
