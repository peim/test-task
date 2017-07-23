package com.peim;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        EventProcessor processor = new EventProcessor();

        for (int i = 0; i < 1000; i++) {
            processor.processEvent(LocalDateTime.now(), new RandomCallable());
        }
    }
}
