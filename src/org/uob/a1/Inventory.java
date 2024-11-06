package org.uob.a1;
public class Inventory {
    public String[] items;
    public String[] descriptions;
    final int MAX_ITEMS = 10;
    public Inventory() {
        items = new String[MAX_ITEMS];
        descriptions = new String[MAX_ITEMS];
    }

    public void addItem(String item) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }
    public void addItem(String item, String description) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == null) {
                items[i] = item;
                descriptions[i] = description;
                break;
            }
        }

    }

    public void removeItem(String item) {
        String[] temp = new String[items.length];
        for (int i = 0; i < temp.length; i++) {
            if (!item.equals(items[i])) {
                temp[i] = items[i];
            }
        }
        items = temp;
    }

    public String displayInventory() {
        String returnString = "";

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                returnString += items[i] + " ";
            }
        }

        return returnString;
    }

    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                empty = false;
            }
        }
        return empty;
    }

    public int hasItem(String item){
        int itemPosition = -1;
        for (int i = 0; i < items.length; i++){
            if (items[i] != null && items[i].equalsIgnoreCase(item)){
                itemPosition = i;
                break;
            }
        }
        return itemPosition;
    }


}