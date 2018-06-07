package com.example.main;

import com.example.disruptor.event.HelloEvent;
import com.example.disruptor.factory.HelloEventFactory;
import com.example.disruptor.handler.HelloEventHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lvhua
 * @Date 2018/6/7 下午5:14
 */
public class DisruptorMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        WaitStrategy blockingWaitStrategy = new BlockingWaitStrategy();
        WaitStrategy sleepingWaitStrategy = new SleepingWaitStrategy();
        WaitStrategy yieldingWaitStrategy = new YieldingWaitStrategy();

        EventFactory<HelloEvent> eventFactory = new HelloEventFactory();

        int ringBufferSize = 1024 * 1024;

        Disruptor<HelloEvent> disruptor = new Disruptor<HelloEvent>(eventFactory,ringBufferSize,executor, ProducerType.SINGLE,blockingWaitStrategy);

        EventHandler<HelloEvent> eventHandler = new HelloEventHandler();

        disruptor.handleEventsWith(eventHandler);

        disruptor.start();

        RingBuffer<HelloEvent> ringBuffer = disruptor.getRingBuffer();

        long seq = ringBuffer.next();
        try {
            HelloEvent event = ringBuffer.get(seq);
            event.setId(1);
            event.setValue("event 1");
        } finally {
            ringBuffer.publish(seq);
        }
    }
}
