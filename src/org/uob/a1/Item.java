package org.uob.a1;

public class Item {
    private String itemName;
    private String itemDescription;
    public boolean canPickUp = false;

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;

    }
    public Item(String itemName, String itemDescription, boolean canPickUp) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.canPickUp = canPickUp;
    }
    public String getName() {
        return itemName;
    }
    public String getDescription() {
        return itemDescription;
    }
}
