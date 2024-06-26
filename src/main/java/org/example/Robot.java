package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

@Getter
@Setter
@AllArgsConstructor
public class Robot implements Runnable {
    private final String name;

    @Override
    @SuppressWarnings("ALL")
    public void run() {
        GlobalClock clock = GlobalClock.getInstance();
        Warehouse warehouse = Warehouse.getInstance();
        Semaphore shipmentPostSemaphore = Semaphores.getShipmentPostSemaphore();
        Semaphore storageSlotSemaphore = Semaphores.getStorageSlotSemaphore();

        System.out.printf("%s is ready to work once more!\n", this.name);

        while(true){
            try {
                boolean isWorkingTime;

                synchronized (clock) {
                    isWorkingTime = clock.isWorkingTime();
                }

                if (isWorkingTime) {
                    shipmentPostSemaphore.acquire();

                    Shipment shipment;
                    ShipmentPost shipmentPost = warehouse.getShipmentPost();

                    synchronized (shipmentPost) {
                        shipment = shipmentPost.getShipment();
                        if (shipment == null) {
                            shipmentPostSemaphore.release();
                            continue;
                        }
                    }

                    shipmentPostSemaphore.release();

                    int slotIndex = warehouse.getSlotIndex(shipment);
                    int timeToSlot = warehouse.getTimeToSlot(shipment);

                    System.out.printf("%s has began walking towards slot %s(No. %d) to drop off shipment №%d.\n"
                            , this.name, shipment.getType().label, slotIndex, shipment.getId());

                    Thread.sleep(Duration.ofSeconds(timeToSlot).toMillis());

                    storageSlotSemaphore.acquire();

                    LinkedList<Shipment> currentSlot = warehouse.getSlot(slotIndex);

                    synchronized (currentSlot) {
                        currentSlot.add(shipment);
                    }
                    System.out.printf("%s has dropped off shipment №%d and is heading towards the shipment post.\n"
                            , this.name, shipment.getId());

                    storageSlotSemaphore.release();

                    Thread.sleep(Duration.ofSeconds(timeToSlot).toMillis());
                } else {
                    System.out.printf("%s has went to sleep in order to charge its battery.\n", this.name);
                    break;
                }
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
