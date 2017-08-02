package com.peim;

import java.time.LocalDateTime;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception{

        EventProcessor processor = new EventProcessor();

        new Thread(processor::process).start();

        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(r.nextInt(50) + 50);
                    processor.addEvent(new Event(LocalDateTime.now(), new RandomCallable()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
