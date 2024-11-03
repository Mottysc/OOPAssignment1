package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    public static void main(String[] args) {
        System.out.println("Hello world");
        Inventory inventory = new Inventory();
        inventory.addItem("apple");
        inventory.addItem("pear");
        inventory.addItem("banana");
        System.out.println(inventory.displayInventory());
        inventory.removeItem("pear");
        System.out.println(inventory.displayInventory());

    }
    
}