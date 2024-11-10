package org.uob.a1;

public class Inventory {
  final int MAX_ITEMS = 10;
  public String[] items;
  public String[] descriptions;

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

  // Descriptions can be read using "look <item>", but not all items need them, so there are two
  // addItem attributes
  public void addItem(String item, String description) {
    for (int i = 0; i < MAX_ITEMS; i++) {
      if (items[i] == null) {
        items[i] = item;
        descriptions[i] = description;
        break;
      }
    }
  }

  // Remove an item from the inventory
  public void removeItem(String item) {
    boolean found = false;
    // Cycle through the items, if the item was just found, move the current item back (to overwrite
    // it), and then move that forward
    for (int i = 0; i < items.length; i++) {
      if (found) {
        items[i - 1] = items[i];
      } else if (item.equalsIgnoreCase(items[i])) {
        found = true;
      }
    }
    if (found) { // To prevent duplication, mark the last item as null
      items[items.length - 1] = null;
    } else {
      System.out.println(item + " not found to remove");
    }
  }

  // Display the inventory
  public String displayInventory() {
    String returnString = "";

    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        returnString += items[i] + " ";
      }
    }
    return returnString;
  }

  // Used if the player types the inventory command with an empty inventory
  public boolean isEmpty() {
    boolean empty = true;
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        empty = false;
        break;
      }
    }
    return empty;
  }

  // Return the index of the item, or -1 if it's not found
  public int hasItem(String item) {
    int itemPosition = -1;
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null && items[i].equalsIgnoreCase(item)) {
        itemPosition = i;
        break;
      }
    }
    return itemPosition;
  }
}
