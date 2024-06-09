package org.example;

import lombok.Getter;

import java.time.Duration;

@Getter
public class GlobalClock implements Runnable {
    @Getter
    private final static GlobalClock instance = new GlobalClock();

    private final static int WORK_DAY_START = 8;
    private final static int WORK_DAY_END = 22;
    private final static int SHIPMENT_DELIVERY_TIME = 9;


    private int hour = 7;
    private int minute = 40;

    void addMinute() {
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

    public boolean isWorkingTime() {
        return this.hour >= 8 && this.hour <= 22;
    }

    public boolean isWorkDayStart() {
        if(this.hour == WORK_DAY_START && this.minute == 0) {
            System.out.println("dadada");
        }

        return this.hour == WORK_DAY_START && this.minute == 0;
    }

    public boolean isShipmentDeliveryTime() {
        return this.hour == SHIPMENT_DELIVERY_TIME && this.minute == 0;
    }

    @Override
    @SuppressWarnings("ALL")
    public void run() {
        while(true) {
            try {
                System.out.printf("%d:%d\n", this.hour, this.minute);
                Thread.sleep(Duration.ofSeconds(1).toMillis() / 4);
                instance.addMinute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
