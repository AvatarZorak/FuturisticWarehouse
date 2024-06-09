package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.ContentType;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

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

    public void receiveDelivery() {
        Random randomNumberGenerator = new Random();
        int shipmentLowerBound = 1;
        int shipmentUpperBound = 40;

        int numberOfNewShipments = randomNumberGenerator
                .nextInt(shipmentUpperBound - shipmentLowerBound) + shipmentLowerBound;

        for(int i = 0; i < numberOfNewShipments; i++) {
            ContentType newShipmentContentType = ContentType
                    .getContentTypeByValue(randomNumberGenerator.nextInt(ContentType.values().length));

            Shipment newShipment = new Shipment(shipmentIdCounter++, newShipmentContentType);

            this.addShipment(newShipment);
        }
    }
}
