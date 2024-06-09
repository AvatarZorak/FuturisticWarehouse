package org.example;

import lombok.Getter;

import java.util.concurrent.Semaphore;

public class Semaphores {
    private final static int SHIPMENT_POST_PERMITS = 2;
    private final static int STORAGE_SLOT_PERMITS = 1;

    @Getter
    private final static Semaphore shipmentPostSemaphore = new Semaphore(SHIPMENT_POST_PERMITS);

    @Getter
    private final static Semaphore storageSlotSemaphore = new Semaphore(STORAGE_SLOT_PERMITS);
}
