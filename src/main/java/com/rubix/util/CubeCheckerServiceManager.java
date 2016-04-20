package com.rubix.util;

import java.util.concurrent.atomic.AtomicInteger;

public class CubeCheckerServiceManager {

    private final int total;

    private final AtomicInteger counter = new AtomicInteger();

    public CubeCheckerServiceManager(final int total) {
        this.total = total;
    }

    public boolean checkFinished() {
        return counter.getAndIncrement() < total;
    }
}
