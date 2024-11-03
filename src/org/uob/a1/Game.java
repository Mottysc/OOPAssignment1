package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.addItem("sword");
        System.out.println(inv.displayInventory());
        System.out.println(inv.hasItem("sword"));
    }
    
}