package com.peim;

import java.util.concurrent.*;

public class EventProcessor {

    private DelayQueue<Event> workQueue = new DelayQueue<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void process() {
        while (true) {
            try {
                Event event = workQueue.take();
                executor.submit(event.getTask());
                System.out.println(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEvent(Event event) {
        workQueue.add(event);
    }
}
