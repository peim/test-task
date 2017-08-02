package com.peim;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Event implements Delayed {

    private static AtomicInteger counter = new AtomicInteger(0);

    private int id;
    private LocalDateTime startTime;
    private Callable<Integer> task;

    public Event(LocalDateTime startTime, Callable<Integer> task) {
        this.startTime = startTime;
        this.task = task;
        this.id = counter.incrementAndGet();
    }

    public Callable<Integer> getTask() {
        return task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long startTimeMillis = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long delay = startTimeMillis - System.currentTimeMillis();
        return unit.convert(delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Event that = (Event) o;
        if (startTime.equals(that.startTime)) return id - that.id;
        else return startTime.compareTo(that.startTime);
    }

    @Override
    public String toString() {
        return "Event{id = " + id + "; startTime = " + startTime + "; task = " + task + "}";
    }
}
