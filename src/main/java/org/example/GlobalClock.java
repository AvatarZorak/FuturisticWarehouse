package org.example;

import lombok.Getter;

import java.time.Clock;
import java.time.Duration;

@Getter
public class GlobalClock implements Runnable {

    @Getter
    private static final GlobalClock instance = new GlobalClock();

    private Integer hour;
    private Integer minute;

    @Override
    @SuppressWarnings("ALL")
    public void run() {
        while(true) {
            try {
                Thread.sleep(Duration.ofSeconds(1).toMillis());
                this.addMinute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addMinute() {
        this.minute++;

        if(this.minute == 60) {
            this.minute = 0;
            this.addHour();
        }
    }

    private void addHour() {
        this.hour++;

        if(this.hour == 24) {
            this.hour = 0;
        }
    }
}
