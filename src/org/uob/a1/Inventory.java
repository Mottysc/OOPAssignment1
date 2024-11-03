package org.uob.a1;

public class Inventory {
   private String[] items;
   final int MAX_ITEMS = 10;
   public Inventory() {
       items = new String[MAX_ITEMS];
   }

   public void addItem(String item) {
       for (int i = 0; i < MAX_ITEMS; i++) {
           if (items[i] == null) {
               items[i] = item;
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

    public int hasItem(String item){
       int itemPosition = -1;
       for (int i = 0; i < items.length; i++){
           if (items[i] == item){
               itemPosition = i;
           }
       }
       return itemPosition;
   }

   
}