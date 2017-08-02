package com.peim;

import java.util.Random;
import java.util.concurrent.Callable;

public class RandomCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int random = new Random().nextInt();
        return random;
    }
}
