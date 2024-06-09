package org.example;

import lombok.Getter;

@Getter
public class GlobalClock {

    @Getter
    private static GlobalClock instance = new GlobalClock();

    private Integer hour = 8;
    private Integer minute = 0;

    public static GlobalClock getInstance() {
        if (instance == null) {
            instance = new GlobalClock();
        }

        return instance;
    }

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

    public boolean isWorkingTime(){
        return this.hour >= 8 && this.hour <= 22;
    }
}
