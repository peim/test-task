package com.peim;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.*;

public class EventProcessor {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

    public synchronized ScheduledFuture<Integer> processEvent(LocalDateTime time, Callable<Integer> task) {
        long taskStartTime = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long delay = taskStartTime - System.currentTimeMillis();
        return executor.schedule(task, delay, TimeUnit.MILLISECONDS);
    }
}
