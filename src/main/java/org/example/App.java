package org.example;

import org.example.enums.ContentType;

import java.util.concurrent.Semaphore;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GlobalClock clock = GlobalClock.getInstance();
        Thread runnableTimer = new Thread(new RunnableTimer(clock));
        runnableTimer.start();
        Warehouse warehouse = new Warehouse();
        Semaphore semaphore = new Semaphore(4);

        Robot robot1 = new Robot("1", clock, semaphore, warehouse);
        Robot robot2 = new Robot("2", clock, semaphore, warehouse);
        Robot robot3 = new Robot("3", clock, semaphore, warehouse);
        Robot robot4 = new Robot("4", clock, semaphore, warehouse);
        Robot robot5 = new Robot("5", clock, semaphore, warehouse);
        Robot robot6 = new Robot("6", clock, semaphore, warehouse);
        Robot robot7 = new Robot("7", clock, semaphore, warehouse);

        ShipmentPost shipmentPost = warehouse.getShipmentPost();

        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.CHAIR));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.MONITOR));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.MOUSE));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.GAME));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.LAMP));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.HEADPHONES));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.LAPTOP));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.LAPTOP));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.KEYBOARD));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.MONITOR));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.DESK));
        shipmentPost.addShipment(new Shipment(shipmentPost.getNewId(), ContentType.DESK));

        new Thread(robot1).start();
        new Thread(robot2).start();
        new Thread(robot3).start();
        new Thread(robot4).start();
        new Thread(robot5).start();
        new Thread(robot6).start();
        new Thread(robot7).start();

    }
}
