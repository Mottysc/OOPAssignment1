package org.uob.a1;

public class Person {
    private String name;
    private Room room;
    private String line;

    //The Person class is used to store the name, room, and line a character says.
    public Person(String name, Room room, String line) {
        this.name = name;
        this.room = room;
        this.line = line;
    }

    public String speak() {
        return name + " says: " + line;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }
}
