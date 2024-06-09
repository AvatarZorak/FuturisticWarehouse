package org.example;

import java.time.Duration;

public class RunnableTimer implements Runnable{
    private final GlobalClock clock;

    public RunnableTimer(GlobalClock clock) {
        this.clock = clock;
    }

    @Override
    @SuppressWarnings("ALL")
    public void run() {
        while(true) {
            try {
                Thread.sleep(Duration.ofSeconds(1).toMillis());
                clock.addMinute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
