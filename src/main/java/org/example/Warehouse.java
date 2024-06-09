package org.example;

import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.ContentType;

import java.util.*;

@Getter
@Setter
//@NoArgsConstructor
public class Warehouse {
    private final ShipmentPost shipmentPost = new ShipmentPost();
    //private final List<Robot> robots = new LinkedList<>();
    private final ArrayList<LinkedList<Shipment>> storageSlots = new ArrayList<>(10);
    private final Map<ContentType, Integer> lookupTable = new HashMap<>();

    public Warehouse(){
        lookupTable.put(ContentType.MOUSE, 0);
        lookupTable.put(ContentType.DESK, 1);
        lookupTable.put(ContentType.CHAIR, 2);
        lookupTable.put(ContentType.GAME, 3);
        lookupTable.put(ContentType.LAMP, 4);
        lookupTable.put(ContentType.HEADPHONES, 5);
        lookupTable.put(ContentType.KEYBOARD, 6);
        lookupTable.put(ContentType.LAPTOP, 7);
        lookupTable.put(ContentType.MONITOR, 8);

        for(int i = 0; i < 9; i++){
            storageSlots.add(new LinkedList<>());
        }
    }

    /*public void addRobot(Robot newRobot) {
        this.robots.add(newRobot);
    }*/

    public ShipmentPost getShipmentPost() {
        return shipmentPost;
    }

    public Integer getAddingTime(Shipment shipment){
        return lookupTable.get(shipment.getType());
    }

    public void addToSlot(Shipment shipment){
        storageSlots.get(lookupTable.get(shipment.getType())).add(shipment);
    }
}
