package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.NoSuchElementException;

@Getter
@Setter
@NoArgsConstructor
public class ShipmentPost {
    private static Integer shipmentIdCounter = 0;

    private final LinkedList<Shipment> storage = new LinkedList<>();

    void addShipment(Shipment shipment){
        storage.addLast(shipment);
    }

    Shipment getShipment(){
        try{
            return storage.removeFirst();
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    public Integer getNewId(){
        shipmentIdCounter++;
        return shipmentIdCounter;
    }
}
