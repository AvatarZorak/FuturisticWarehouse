package org.example.enums;

public enum ContentType {
    KEYBOARD(0, "Keyboard"),
    MOUSE(1, "Mouse"),
    HEADPHONES(2, "Headphones"),
    LAPTOP(3, "Laptop"),
    MONITOR(4, "Monitor"),
    GAME(5, "Game"),
    DESK(6, "Desk"),
    CHAIR(7, "Chair"),
    LAMP(8, "Lamp");

    public final int value;
    public final String label;

    ContentType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static ContentType getContentTypeByValue(int value) {
        for(ContentType ct : ContentType.values()) {
            if(ct.value == value) {
                return ct;
            }
        }

        throw new RuntimeException("Could not fine content type with this value!");
    }

}
