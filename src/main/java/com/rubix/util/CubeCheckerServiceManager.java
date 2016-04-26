package com.rubix.util;

import java.util.concurrent.atomic.AtomicInteger;

public class CubeCheckerServiceManager {

    private final int total;

    private final AtomicInteger counter = new AtomicInteger();

    public CubeCheckerServiceManager(final int total) {
        this.total = total;
    }

    public boolean checkFinished() {
        // System.out.println(counter.incrementAndGet());
        // return counter.get() == total;
        return counter.incrementAndGet() == total;
    }
}
