package org.example.enums;

public enum ContentType {
    KEYBOARD("Keyboard"),
    MOUSE("Mouse"),
    HEADPHONES("Headphones"),
    LAPTOP("Laptop"),
    MONITOR("Monitor"),
    GAME("Game"),
    DESK("Desk"),
    CHAIR("Chair"),
    LAMP("Lamp");

    public final String label;

    ContentType(String label) {
        this.label = label;
    }

}
