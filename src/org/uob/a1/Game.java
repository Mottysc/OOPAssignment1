package org.uob.a1;
import java.util.Scanner;
public class Game {
    static Score score = new Score(100);
    static Inventory inv = new Inventory();
    static Position player = new Position(1, 0);
    static Map map = new Map(6, 5);
    static Room currentRoom;
    static Room[] rooms;
    static boolean quit = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Creating the areas
        Room kitchen = new Room("the Kitchen", "You notice a hastily-written ToDo list stuck on the fridge. Looking out of the window, you can see the snowfall picking up. It's going to be a cold night. To the south you see your living room.", 'k', new Position(1, 0));
        map.placeRoom(kitchen.getPosition(), kitchen.getSymbol());
        Room livingRoom = new Room("the Living Room", "You look around and see your menorah near the window, with drips of wax from years gone by. Your coffee table is adorned with a bowl full of gelt.", 'l', new Position(1, 1));
        map.placeRoom(livingRoom.getPosition(), livingRoom.getSymbol());
        Room hallway = new Room("the Hallway", "", 'h', new Position(2, 1));
        map.placeRoom(hallway.getPosition(), hallway.getSymbol());
        Room grandma = new Room("Grandma's House", "", 'g', new Position(4, 3));
        map.placeRoom(grandma.getPosition(), grandma.getSymbol());
        Room shop = new Room("the Shop", "", 's', new Position(0, 4));
        map.placeRoom(shop.getPosition(), shop.getSymbol());
        Room neighbour = new Room("the Neighbour's House", "", 'n', new Position(5, 4));
        map.placeRoom(neighbour.getPosition(), neighbour.getSymbol());
        Room diningRoom = new Room("the Dining Room", "", 'd', new Position(3, 1));
        map.placeRoom(diningRoom.getPosition(), diningRoom.getSymbol());
        Room attic = new Room("the Attic", "", 'a', new Position(3, 0));
        map.placeRoom(attic.getPosition(), attic.getSymbol());
        Room westRoad = new Room("the Road", "", 'r', new Position(1, 4));
        map.placeRoom(westRoad.getPosition(), westRoad.getSymbol());
        Room eastRoad = new Room("the Road", "", 'r', new Position(4, 4));
        map.placeRoom(eastRoad.getPosition(), eastRoad.getSymbol());
        Room northRoad = new Room("the Road", "", 'r', new Position(2, 2));
        map.placeRoom(northRoad.getPosition(), northRoad.getSymbol());
        map.placeRoom(new Position(2, 3), 'r');
        map.placeRoom(new Position(2, 4), 'r');
        map.placeRoom(new Position(3, 4), 'r');

        rooms = new Room[]{kitchen, livingRoom, diningRoom, attic, westRoad, eastRoad, northRoad, grandma, neighbour, hallway, shop};

        Item todoList = new Item("Todo list", "You read the todo list, it says to go shopping, fry latkes, prepare the menorah, and put up the decorations.");
        kitchen.setItems(new Item[]{todoList});
        currentRoom = kitchen;
        System.out.print("You enter " + currentRoom.getName() + ". ");
        System.out.println(currentRoom.getDescription());
        inv.addItem("chocolate coins");
        inv.addItem("latkes", "it's potatoey");
        while (!quit) {
            System.out.print(">> ");
            String commandInput = input.nextLine();
            processCommand(commandInput);

        }
    }

    public static void processCommand(String command) {
        //set up processing the command
        String[] parts = command.split(" ", 2);
        boolean hasFeature = false;
        String commandWord = parts[0].toLowerCase();
        String feature = null;
        //see if an item/direction was specified
        if (parts.length > 1){
            feature = parts[1].toLowerCase();
            hasFeature = true;
        }

        switch (commandWord) {
            case "look":
                if (!hasFeature) {
                    System.out.println(currentRoom.getDescription());
                }
                else {
                    lookItem(feature, currentRoom);
                }
                break;

            case "score":
                System.out.println("Your current score is: " + score.getScore());
                break;

            case "move":
                if (!hasFeature) {
                    System.out.println("You spin around and decide you'd rather move in a specific direction.");
                }
                else {
                    switch (feature) {
                        case "north":
                            if (player.y != 0 && tryMove(player, 0, -1)){
                                player.y -= 1;
                            }
                            else {
                                System.out.println("You realise there's nothing for you if you move north.");
                            }
                            break;
                        case "east":
                            if (player.y != map.width-1 && tryMove(player, +1, 0)){
                                player.x += 1;
                            }
                            else {
                                System.out.println("You realise there's nothing for you if you move east.");
                            }
                            break;
                        case "south":
                            if (player.y != map.height-1 && tryMove(player, 0, 1)){
                                player.y += 1;
                            }
                            else {
                                System.out.println("You realise there's nothing for you if you move south.");

                            }
                            break;
                        case "west":
                            if (player.x != 0 && tryMove(player, -1, 0)){
                                player.x -= 1;

                            }
                            else {
                                System.out.println("You realise there's nothing for you if you move west.");
                            }
                            break;
                        default:
                            System.out.println("You don't know how to move like that.");
                            break;
                    }
                }
                //update the current room and print the room description if you enter a new room.
                Room oldroom = currentRoom;
                for (Room room : rooms) {
                    if (room.getPosition().y == player.y && room.getPosition().x == player.x) {
                        currentRoom = room;
                        if (!currentRoom.equals(oldroom)){
                            System.out.print("You enter " + currentRoom.getName() + ". ");
                            System.out.println(currentRoom.getDescription());
                        }
                        break;
                    }
                }
                break;
            case "map":
                System.out.println(map.display());
                break;

            case "inventory":
                if (inv.isEmpty()){
                    System.out.println("You don't have any items in your inventory.");
                }
                else {
                    System.out.println("You look into your pockets and see: " + inv.displayInventory());
                }
                break;

            case "quit":
                quit = true;
                System.out.println("Goobye.");
                break;
            case "help":
                String helpMessage = "Here are the commands you can use:\n"
                        + "• \"move <direction>\" - (<direction> can be \"north\", \"south\", \"east\", \"west\"). The player moves to a new room based on the direction.\n"
                        + "• \"look\" - Displays a description of the room the player is in.\n"
                        + "• \"look <feature>\" - Displays a more detailed description of a feature of a room.\n"
                        + "• \"look <item>\" - Displays a description of an item in your inventory.\n"
                        + "• \"inventory\" - Displays a list of all items the player has obtained.\n"
                        + "• \"score\" - Displays the user’s current score.\n"
                        + "• \"map\" - Displays a text-based map of the current explored game world.\n"
                        + "• \"help\" - Displays a help message.\n"
                        + "• \"quit\" - Quits the game\n"
                        + "\n";
                System.out.println(helpMessage);
                break;

            default:
                System.out.println("You're not sure what to do right now...");
        }
    }

    public static boolean tryMove(Position currentPosition, int xDelta, int yDelta) {
        return map.map[currentPosition.y + yDelta][currentPosition.x + xDelta] != '.';
    }

    public static void lookItem(String itemName, Room currentRoom){
        if (inv.hasItem(itemName) != -1){
            if (inv.descriptions[inv.hasItem(itemName)] != null){

                System.out.println(inv.descriptions[inv.hasItem(itemName)]);
            }
            else {
                System.out.println("You can't exactly describe the item, but it's in your pocket.");
            }
        }
        else {
            for (Item item : currentRoom.getItems()){
                if (item.getName().equalsIgnoreCase(itemName)){
                    System.out.println(item.getDescription());
                }
                else {
                    System.out.println("You can't seem to see that item.");
                }
            }}
    }

}