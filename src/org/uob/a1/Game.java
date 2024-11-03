package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    public static void main(String[] args) {
        System.out.println("Hello world");
        Room kitchen = new Room("kitchen", "yeah", 'k', new Position(2, 3));
        Map theMap = new Map(5, 5);
        System.out.println(kitchen.getDescription());
        theMap.placeRoom(kitchen.getPosition(), kitchen.getSymbol());
        System.out.println(theMap.display());
    }
    
}