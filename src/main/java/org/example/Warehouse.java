package org.example;

import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.ContentType;

import java.util.*;

@Getter
@Setter
//@NoArgsConstructor
public class Warehouse implements Runnable {
    @Getter
    private static final Warehouse instance = new Warehouse();

    private static final int numberOfRobots = 10;

    @Getter
    private final ShipmentPost shipmentPost = new ShipmentPost();

    private final List<Robot> robots = new LinkedList<>();
    private final ArrayList<LinkedList<Shipment>> storageSlots = new ArrayList<>(10);
    private final Map<ContentType, Integer> lookupTable = new HashMap<>();

    private boolean areRobotsWorking = false;

    public Warehouse(){
        this.setLookupTable();

        for(int i = 0; i < 9; i++){
            storageSlots.add(new LinkedList<>());
        }

        for(int i = 0; i < numberOfRobots; i++) {
            this.robots.add(new Robot(String.format("Robot_%d", i + 1)));
        }
    }

    private void startRobots() {
        System.out.println("Robots have began their work");

        for(Robot r : this.robots) {
            Thread newRobot = new Thread(r);
            System.out.print("da ");
            newRobot.start();
        }
    }

    private void setLookupTable() {
        int currentSlot = 0;
        for(ContentType ct : ContentType.values()) {
            lookupTable.put(ct, currentSlot++);
        }
    }

    public int getSlotIndex(Shipment shipment){
        return lookupTable.get(shipment.getType());
    }

    public int getTimeToSlot(Shipment shipment) {
        return getSlotIndex(shipment) + 1;
    }

    public LinkedList<Shipment> getSlot(int slotIndex) {
        return this.storageSlots.get(slotIndex);
    }

    @Override
    @SuppressWarnings("All")
    public void run() {
        while(true) {
            if(GlobalClock.getInstance().isShipmentDeliveryTime()) {
                this.shipmentPost.receiveDelivery();
            }

            if(GlobalClock.getInstance().isWorkDayStart() && !this.areRobotsWorking) {
                System.out.println("yes");
                this.startRobots();
                this.areRobotsWorking = true;
            }
        }
    }
}
