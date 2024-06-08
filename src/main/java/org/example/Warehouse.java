package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.ContentType;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
    private final ShipmentPost shipmentPost = new ShipmentPost();
    private final List<Robot> robots = new LinkedList<>();
    private final ArrayList<LinkedList<Shipment>> storageSlots = new ArrayList<>();
    private final Map<ContentType, Integer> lookupTable = new HashMap<>();

    public void addRobot(Robot newRobot) {
        this.robots.add(newRobot);
    }
}
