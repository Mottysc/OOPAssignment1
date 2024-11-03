package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    public static void main(String[] args) {
        Map theMap = new Map(5, 5);
        theMap.placeRoom(new Position(3, 1), 'c');
        theMap.placeRoom(new Position(0, 0), 'r');
        System.out.println(theMap.display());
    }
    
}