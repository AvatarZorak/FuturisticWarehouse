package org.example;

import lombok.Getter;

import java.util.concurrent.Semaphore;

public class Semaphores {
    @Getter
    private final static Semaphore shipmentPostSemaphore = new Semaphore(2);

    @Getter
    private final static Semaphore storageSlotSemaphore = new Semaphore(1);
}
