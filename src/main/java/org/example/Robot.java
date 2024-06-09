package org.example;

//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.concurrent.Semaphore;

@Getter
@Setter
//@AllArgsConstructor
public class Robot implements Runnable {
    private final String name;
    private final GlobalClock clock;

    private final Semaphore semaphore;
    private final Warehouse warehouse;

    public Robot(String name, GlobalClock clock, Semaphore semaphore, Warehouse warehouse) {
        this.name = name;
        this.clock = clock;
        this.semaphore = semaphore;
        this.warehouse = warehouse;
    }

    @Override
    @SuppressWarnings("ALL")
    public void run() {
        while(true){
            try {
                if (clock.isWorkingTime()) {
                    semaphore.acquire();

                    Shipment shipment;
                    synchronized (warehouse) {
                        shipment = warehouse.getShipmentPost().getShipment();
                        if (shipment == null) {
                            semaphore.release();
                            continue;
                        }
                    }

                    System.out.printf("Robot %s is adding shipment №%d to a slot %s\n", this.name, shipment.getId(), shipment.getType().label);
                    Thread.sleep(Duration.ofSeconds(warehouse.getAddingTime(shipment)).toMillis());

                    synchronized (warehouse){
                        warehouse.addToSlot(shipment);
                    }

                    semaphore.release();
                }
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
