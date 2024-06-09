package org.example;

public class App 
{
    public static void main( String[] args )
    {
        Thread globalClock = new Thread(GlobalClock.getInstance());
        globalClock.start();

        Thread warehouse = new Thread(Warehouse.getInstance());
        warehouse.start();
    }
}
