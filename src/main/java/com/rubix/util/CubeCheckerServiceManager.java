package com.rubix.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rubix.constant.CubeConstants;

@Scope("prototype")
@Component
public class CubeCheckerServiceManager {

    private final int total;

    private final AtomicInteger counter = new AtomicInteger();

    public CubeCheckerServiceManager() {
        this.total = CubeConstants.TOTAL_NR_OF_COMBINATIONS;
    }

    public CubeCheckerServiceManager(final int total) {
        this.total = total;
    }

    public boolean checkFinished() {
        System.out.println(counter.incrementAndGet());
        return counter.get() == total;
        // return counter.incrementAndGet() == total;
    }
}
