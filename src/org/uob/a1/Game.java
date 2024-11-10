package org.uob.a1;

import java.util.Scanner;

public class Game {
    //Set-up of the globally used variables
    static Score score = new Score(100);
    static Inventory inv = new Inventory();
    static Position player = new Position(1, 0); //Set up the starting position of the player, this is also used to ensure the player is in the right room when taking items, etc
    static Map map = new Map(6, 5);
    static Room[] rooms;
    static Room currentRoom;
    static Person[] people;
    static boolean quit = false;
    static boolean wearingBoots = false; //Used for one of the puzzles
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        //Creating the Room objects and placing them on the map
        Room kitchen = new Room("the Kitchen", "The kitchen feels cosy, with a hastily-written ToDo list on the fridge and an empty frying pan on the counter.\nTo the south, you see the entrance to the living room, while outside, snow falls softly, blanketing everything in white.", 'k', new Position(1, 0));
        map.placeRoom(kitchen.getPosition(), kitchen.getSymbol());
        Room livingRoom = new Room("the Living Room", "You look around and see your menorah near the window with drips of wax from years gone by, next to it are the candles in a neat pile waiting to be lit. \nYour coffee table is adorned with a bowl full of gelt.\nTo your east is the hallway, and to the north is the kitchen.", 'l', new Position(1, 1));
        map.placeRoom(livingRoom.getPosition(), livingRoom.getSymbol());
        Room hallway = new Room("the Hallway", "A cluttered hallway lies before you. Your family's coats and scarves are hung up, waiting to be used. \nYou can see your boots laying on the floor, still covered with bits of snow from earlier in the day. \nYour front door is to the south of you, your living room to the west, and the dining room in the east.", 'h', new Position(2, 1));
        map.placeRoom(hallway.getPosition(), hallway.getSymbol());
        Room grandmaHouse = new Room("Grandma's House", "Grandma's house is warm and inviting, with the soft glow of the fireplace and the scent of fresh cookies in the air. \nGrandma sits in her favourite chair, knitting with a gentle smile, surrounded by cosy blankets and an old photo on the wall.", 'g', new Position(5, 4));
        map.placeRoom(grandmaHouse.getPosition(), grandmaHouse.getSymbol());
        Room shop = new Room("the Shop", "The shop is small but cosy, with shelves lined with potatoes, bottles of oil, bags of flour, some sweets, and other tasty goods.\nThe warm light spills from the window, casting a friendly glow over the space.\nBehind the counter, a kind-looking shopkeeper greets you with a smile, her eyes friendly and welcoming as she tidies the cluttered shelves.", 's', new Position(0, 4));
        map.placeRoom(shop.getPosition(), shop.getSymbol());
        Room neighbour = new Room("the Neighbour's House", "The neighbour's house is quiet, dimly lit with soft light through the curtains. Inside, a young boy sits by the window, looking out with a sad expression.", 'n', new Position(3, 3));
        map.placeRoom(neighbour.getPosition(), neighbour.getSymbol());
        Room diningRoom = new Room("the Dining Room", "The table is laid, ready for a nice meal. You see your mother sitting near the table, relaxing. You notice a small gift box next to her.", 'd', new Position(3, 1));
        map.placeRoom(diningRoom.getPosition(), diningRoom.getSymbol());
        Room attic = new Room("the Attic", "The attic is dim and dusty, filled with storage boxes of decorations and childhood memories. A small window lets in a sliver of moonlight, casting shadows over the cluttered space.", 'a', new Position(3, 0));
        map.placeRoom(attic.getPosition(), attic.getSymbol());
        Room westRoad = new Room("the Road", "The road is quiet, lit by street lamps casting soft glows on the snowy path.\nTo the east, the road forks toward your house and the nearby homes, while to the west, the shop stands with its frosted windows and wooden sign gently swaying in the cold breeze.", 'r', new Position(1, 4));
        map.placeRoom(westRoad.getPosition(), westRoad.getSymbol());
        Room eastRoad = new Room("the Road", "A gentle glow comes from Grandma's house to the east, a nice contrast to the dark road. The path back to the fork lies westward, while the faint sounds of nearby houses blend into the still night.", 'r', new Position(4, 4));
        map.placeRoom(eastRoad.getPosition(), eastRoad.getSymbol());
        Room northRoad = new Room("the Road", "You see your house to the north, with lights shining in the living room and dining room against the dark night. The road is quiet and deserted, only lit by street lamps to the south.", 'r', new Position(2, 2));
        map.placeRoom(northRoad.getPosition(), northRoad.getSymbol());
        Room northMidRoad = new Room("the Road", "As you continue down the road, you see houses with the warm glows of Christmas trees and menorahs.\nSnow continues to fall as you walk, crunching beneath your steps. Your house is further north, and the fork in the road lies to the south.", 'r', new Position(2, 3));
        map.placeRoom(northMidRoad.getPosition(), northMidRoad.getSymbol());
        Room splitRoad = new Room("the Corner Road", "The road splits here. You can walk north to your house, east to Grandma's or the neighbour's house, or west to the shop.", 'r', new Position(2, 4));
        map.placeRoom(splitRoad.getPosition(), splitRoad.getSymbol());
        Room eastMidRoad = new Room("the Road", "As you walk down the road, you see your neighbour's house to the north. To the east is Grandma's house, and the fork in the road lies further west.", 'r', new Position(3, 4));
        map.placeRoom(eastMidRoad.getPosition(), eastMidRoad.getSymbol());

        rooms = new Room[]{kitchen, livingRoom, diningRoom, attic, westRoad, eastRoad, northRoad, grandmaHouse, neighbour, hallway, shop, northMidRoad, splitRoad, eastMidRoad};

        // The Item class is used for features in rooms as well as items that can be picked up
        Item todoList = new Item("Todo list", "You read the ToDo list, it says to go shopping, fry latkes, prepare the menorah, and put up decorations. \nYou should probably help your mum with some of these.");
        Item pan = new Item("Frying Pan", "The frying pan is on the counter, ready to make latkes.");
        Item menorah = new Item("Menorah", "It's your childhood menorah, showing signs of time with little dents and scratches. Next to it is a neat assortment of candles.");
        Item candles = new Item("Candles", "The candles to light the menorah. There are some differently coloured ones, but you can't remember the order you prefer to place them.", true);
        Item boots = new Item("Boots", "Your favourite pair of boots, perfect for staying warm outside.", true);
        Item gelt = new Item("Gelt", "Golden chocolate coins, a Hanukkah favourite, reminding you of your childhood.", true);
        Item gift = new Item("Gift", "A gift for Grandma. You should probably bring it to her.", true);
        Item potatoes = new Item("Potatoes", "A sack of potatoes, essential for making latkes for your mum.", true);
        Item oil = new Item("Oil", "A bottle of oil, just what you need for frying latkes.", true);
        Item flour = new Item("Flour", "A packet of flour. You don't need it, so you'll be honest and leave it behind.");
        Item sweets = new Item("Sweets", "Tasty-looking sweets. Your mother might not be thrilled if you bring these home...");
        Item oldBox = new Item("Box", "An old box filled with decorations and keepsakes.");
        Item decorations = new Item("Decorations", "Decorations for outside. Your mum would be really pleased if you put these up.", true);
        Item keepsakes = new Item("Keepsakes", "Delicate keepsakes your mum has saved over the years.");
        Item photo = new Item("Photo", "An old family photo Grandma cherishes. It shows your family: Uncle Richard, Aunt Barbara, Grandma, and your dad Patrick.");

        //Adding the items to their respective rooms, this is to ensure that the player is in the right room when interacting with a feature/item
        kitchen.setItems(new Item[]{todoList, pan});
        livingRoom.setItems(new Item[]{candles, menorah, gelt});
        hallway.setItems(new Item[]{boots});
        shop.setItems(new Item[]{potatoes, flour, sweets, oil});
        diningRoom.setItems(new Item[]{gift});
        grandmaHouse.setItems(new Item[]{photo});
        attic.setItems(new Item[]{oldBox, decorations, keepsakes});

        //Creating the people and their line of dialogue
        Person mother = new Person("Your mother", diningRoom, "Please make sure you finish all the items on your ToDo list that I left on the fridge. Also, go give your grandma a visit, she would appreciate your presence.");
        Person grandma = new Person("Grandma", grandmaHouse, "Hey! I'm so glad to decided to come visit me! ");
        Person neighbourKid = new Person("Kid", neighbour, "Hey neighbour! I don't have any Hanukkah Gelt this year, do you have any? I would really appreciate it!");
        Person shopkeeper = new Person("Shopkeeper", shop, "Hey, welcome to the shop. As it's the season of giving, everything will be free tonight, take whatever you need. Happy holidays!");
        people = new Person[]{mother, grandma, neighbourKid, shopkeeper};

        //Starting the game
        currentRoom = kitchen;
        System.out.println("You wake up from an afternoon nap after school, you feel groggy from the sleep but are excited for the festivities tonight.");
        System.out.print("You enter " + currentRoom.getName() + ". ");
        System.out.println(currentRoom.getDescription());

        while (!quit) {
            System.out.print(">> ");
            String commandInput = input.nextLine();
            processCommand(commandInput);

        }
    }

    //Used to process the input
    public static void processCommand(String command) {
        //Set up processing the command
        String[] parts = command.split(" ", 2);
        boolean hasFeature = false; //For example, "look <feature>" or "move north"
        String commandWord = parts[0].toLowerCase();
        String feature = null;
        //See if an item/feature/direction was specified
        if (parts.length > 1) {
            feature = parts[1].toLowerCase();
            hasFeature = true;
        }

        switch (commandWord) {
            case "look":
                if (!hasFeature) {
                    System.out.println(currentRoom.getDescription());
                } else {
                    lookItem(feature, currentRoom);
                }
                break;

            case "score":
                System.out.println("Your current score is: " + score.getScore());
                break;

            case "move":
                if (!hasFeature) { //If no direction is provided
                    System.out.println("You spin around and decide you'd rather move in a specific direction.");
                } else {
                    switch (feature) {
                        case "north":
                            if (player.y != 0 && tryMove(player, 0, -1)) {
                                player.y -= 1;
                            } else {
                                System.out.println("You realise there's nothing for you if you move north.");
                            }
                            break;
                        case "east":
                            if (player.y != map.width - 1 && tryMove(player, +1, 0)) {
                                player.x += 1;
                            } else {
                                System.out.println("You realise there's nothing for you if you move east.");
                            }
                            break;
                        case "south":
                            if (player.y != map.height - 1 && tryMove(player, 0, 1)) {
                                //If you're in the Hallway, there's a puzzle to ensure that the player is wearing boots before leaving the house
                                if (currentRoom == rooms[9]) {
                                    if (wearingBoots) {
                                        player.y += 1;
                                    } else {
                                        System.out.println("It's getting cold outside, you should find your boots and put them on before leaving.");
                                    }
                                } else {
                                    player.y += 1;
                                }
                            } else {
                                System.out.println("You realise there's nothing for you if you move south.");

                            }
                            break;
                        case "west":
                            if (player.x != 0 && tryMove(player, -1, 0)) {
                                player.x -= 1;

                            } else {
                                System.out.println("You realise there's nothing for you if you move west.");
                            }
                            break;
                        default:
                            System.out.println("You don't know how to move like that.");
                            break;
                    }
                }
                //Update the current room and print the room description if you enter a new room.
                Room oldroom = currentRoom;
                for (Room room : rooms) {
                    if (room.getPosition().y == player.y && room.getPosition().x == player.x) {
                        currentRoom = room;
                        if (!currentRoom.equals(oldroom)) {
                            System.out.println("You enter " + currentRoom.getName() + ". ");
                            score.visitRoom();
                        }
                        break;
                    }
                }
                break;

            case "map":
                System.out.println(map.display());
                break;

            case "talk":
                if (!hasFeature) {
                    System.out.println("You mutter something to yourself");
                } else {
                    switch (feature) {
                        case "mum", "mother", "your mother", "your mum", "to mum":
                            if (currentRoom == people[0].getRoom()) {
                                System.out.println(people[0].speak());
                            } else {
                                System.out.println("You think you should go find your mother and talk to her. She might be in the dining room");
                            }
                            break;
                        case "grandma", "grandmother", "your grandma", "your grandmother", "to grandma":
                            if (currentRoom == people[1].getRoom()) {
                                System.out.print(people[1].speak());
                                if (inv.hasItem("gift") != -1) {
                                    //There is an optional puzzle I added, if you bring the gift to your grandma, she thanks you and you gain points.
                                    System.out.println("Thank you so much for bringing me a gift, I really do appreciate it!\n+10 Points!");
                                    score.solvePuzzle();
                                } else {
                                    System.out.println(" ");
                                }
                            } else {
                                System.out.println("You think you should travel to your grandma's house and talk to her.");
                            }
                            break;

                        case "kid", "neighbour", "neighbour's kid", "to kid":
                            if (currentRoom == people[2].getRoom()) {
                                System.out.println(people[2].speak());
                            } else {
                                System.out.println("You think that the neighbour's kid is most likely at their house at this time of the night.");
                            }
                            break;

                        case "shopkeeper":
                            if (currentRoom == people[3].getRoom()) {
                                System.out.println(people[3].speak());
                            } else {
                                System.out.println("You're not sure why you want to talk to the shopkeeper now. You're not even in the shop.");
                            }
                            break;

                        default:
                            System.out.println("You don't know anyone who goes by that name.");
                            break;
                    }
                }
                break;

            case "take":
                if (!hasFeature) {
                    System.out.println("You don't know what to pick up.");
                } else {
                    boolean found = false;
                    for (Item item : currentRoom.getItems()) {
                        if (item != null && feature.equalsIgnoreCase(item.getName())) {
                            if (item.canPickUp) {
                                Item[] copyItems = currentRoom.getItems();
                                for (int i = 0; i < copyItems.length; i++) {
                                    //Remove the item from the room
                                    if (item.equals(copyItems[i])) {
                                        copyItems[i] = null;
                                        break;
                                    }
                                }
                                currentRoom.setItems(copyItems);
                                inv.addItem(item.getName(), item.getDescription());
                                System.out.println("You pick up the " + item.getName() + ".");
                                found = true;
                                break;
                            } else {
                                System.out.println("You know you shouldn't pick that up.");
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("You can't find that item to pick up.");
                    }
                }
                break;

            case "inventory":
                if (inv.isEmpty()) {
                    System.out.println("You don't have any items in your inventory.");
                } else {
                    System.out.println("You look into your pockets and see: " + inv.displayInventory());
                }
                break;

            //Use an item in your inventory
            case "use":
                if (!hasFeature) {
                    System.out.println("You want to use an item in your inventory, but aren't sure what specifically.");
                } else {
                    switch (feature) {
                        case "boots":
                            if (inv.hasItem("boots") != -1) {
                                if (player == rooms[9].getPosition()) {
                                    System.out.println("You put on your boots and are now ready to venture outside\n+10 Points!");
                                    inv.removeItem("boots");
                                    score.solvePuzzle();
                                    wearingBoots = true;
                                } else {
                                    System.out.println("You should go to the hallway to put on your boots.");
                                }
                            } else {
                                System.out.println("You don't have your boots in your hands.");
                            }
                            break;
                        case "candles":
                            if (inv.hasItem("candles") != -1) {
                                if (player == rooms[1].getPosition()) {
                                    playMastermind();
                                } else {
                                    System.out.println("These candles are for your menorah in your living room, you should use them there.");
                                }
                            } else {
                                System.out.println("You can't use the candles if you haven't picked them up.");
                            }
                            break;

                        case "gift":
                            if (inv.hasItem("gift") != -1) {
                                if (player != rooms[7].getPosition()) {
                                    System.out.println("You should probably go give your gift to your grandma.");
                                }
                            } else {
                                System.out.println("You don't have any gift on you, maybe there's one somewhere at home.");
                            }
                            break;

                        case "potatoes", "oil":
                            if (inv.hasItem("potatoes") != -1 && inv.hasItem("oil") != -1) {
                                if (player == rooms[0].getPosition()) {
                                    System.out.println("You peel the potatoes, grate them, add oil to the pan, and turn on the stove.\nYou create the latkes patties and carefully place them into the hot oil. \nThey sizzle as they turn a golden-brown colour before you remove them, ready to eat.\nYou have successfully made the latkes.\n+10 Points!");
                                    inv.removeItem("oil");
                                    inv.removeItem("potatoes");
                                    score.solvePuzzle();
                                } else {
                                    System.out.println("You should save the potatoes for when you're in the kitchen and make latkes for your mother.");
                                }
                            } else {
                                if (inv.hasItem("oil") != -1) {
                                    System.out.println("You should probably hurry back and also get oil from shop to fry the latkes...");
                                } else {
                                    System.out.println("You don't have any potatoes on you, you should get some from the shop.");
                                }
                            }
                            break;

                        case "gelt":
                            if (inv.hasItem("gelt") != -1) {
                                if (player == rooms[8].getPosition()) {
                                    System.out.println("The neighbour's kid is very grateful for your Hanukkah Gelt. He looks very happy with himself, and it warms your heart.\n+10 Points!");
                                    inv.removeItem("gelt");
                                    score.solvePuzzle();
                                }
                            } else {
                                System.out.println("You don't have any Hanukkah Gelt on you. You should check your house.");
                            }
                            break;

                        case "decorations":
                            if (inv.hasItem("decorations") != -1) {
                                if (player == rooms[6].getPosition()) {
                                    System.out.println("You work hard and place the decorations along the front of your house. Despite the cold outside, you manage to work up a sweat.\nYou stand back and admire your work, feeling happy with yourself.\n+10 Points!");
                                    inv.removeItem("decorations");
                                    score.solvePuzzle();
                                } else {
                                    System.out.println("You want to put up the decorations, but should go to the front of your house for that.");
                                }
                            }
                            break;

                        default:
                            System.out.println("You should specify something from in your inventory to use.");
                            break;
                    }
                }
                break;

            case "quit":
                quit = true;
                System.out.println("Goodbye.");
                break;

            case "help":
                //Display the help message
                String helpMessage = """
                        Here are the commands you can use:
                         • "help" - Displays this help message.
                         • "inventory" - Displays a list of all the items you have.
                         • "look" - Displays a description of the room you are in.
                         • "look <feature>" - Displays a more detailed description of a feature in the room.
                         • "look <item>" - Displays a description of an item in your inventory.
                         • "map" - Displays a map of your local area.
                         • "move <direction>" - (<direction> can be "north", "east", "south", or "west").
                         • "take <item>" - Pick up an item in front of you.
                         • "talk <person>" - Interact with someone in the same room as you.
                         • "score" - Displays your current score.
                         • "use <item>" - Use an item from your inventory.
                         • "quit" - Quits the game
                        """;
                System.out.println(helpMessage);
                break;

            default:
                //If none of the valid commands were provided
                System.out.println("You're not sure what to do right now...");
        }
    }

    public static boolean tryMove(Position currentPosition, int xDelta, int yDelta) {
        //Ensure that the area the player is moving to is not empty
        return map.map[currentPosition.y + yDelta][currentPosition.x + xDelta] != '.';
    }

    public static void playMastermind() {
        //This is a mastermind-style game, where the player needs to work out the right order of the candles. They get told how close they are with their guess each time.
        System.out.println("""
                You are trying to remember the order of the candles. You know that you only need to use four candles today.
                The candles available to you are:
                Red, Orange, Yellow, Green, Blue, Purple, White, Lime, Turquoise, and Cyan.
                To figure out the right order, use the first initial of each colour and guess the correct order.
                For example, to enter two green and two red candles, type "ggrr".
                You will then receive an answer based on your guess.
                The candles can either be the right colour in the wrong place, the right colour and the right place, or the wrong colour.
                Type "give up" to stop playing.
                For a hint, type "hint\"""");

        boolean playing = true;
        boolean rightOrder = false;
        final String ORDER = "RBGP";
        final String VALID_COLORS = "RGBPYCTWOC";

        while (playing && !rightOrder) {
            boolean validChars = true;
            System.out.print("->> ");
            String guess = input.nextLine().toUpperCase();

            if (guess.equals("GIVE UP")) {
                playing = false;
                System.out.println("You decide to stop putting the candles in the menorah for now.");
                break;
            } else if (guess.equals("HINT")) {
                System.out.println("You notice a piece of paper with the words \"Grandma's photo\" scribbled on it on the side.");
                continue;
            }

            if (guess.length() != 4) {
                System.out.println("You only need four candles.");
                continue;
            }

            //Ensure that only the right letters were included in the guess
            for (int i = 0; i < 4; i++) {
                char character = guess.charAt(i);
                if (VALID_COLORS.indexOf(character) == -1) {
                    validChars = false;
                    System.out.println("One of the colours you provided is invalid. The colours available to you are:\nRed, Orange, Yellow, Green, Blue, Purple, White, Lime, Turquoise, and Cyan.");
                    break;
                }
            }
            if (!validChars) {
                continue;
            }

            int greens = 0; //A green letter is in the right place as the solution
            int yellows = 0; //A yellow letter is in the correct solution but not in the right place
            //A grey letter (a counter is not needed) is not in the correct solution
            int[] matches = new int[4]; //Used to track the "colour" of the guesses, 0 is grey, 1 is green, 2 is yellow

            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == ORDER.charAt(i)) {
                    matches[i] = 1;
                    greens++;
                } else { //If it's not the right letter in the right place
                    for (int j = 0; j < 4; j++) { //Loop through the letters again
                        if (matches[j] == 0 && guess.charAt(j) == ORDER.charAt(i) && i != j) { //If the letter hasn't been checked before and it's somewhere in the solution,
                            matches[j] = 2; //It's marked as yellow
                            yellows++;
                            break;
                        }
                    }
                }
            }

            //The results are then printed
            System.out.println(greens + (greens == 1 ? " is " : " are ") + "in the menorah and " + (greens == 1 ? "is" : "are") + " in the right place, and " + yellows + (yellows == 1 ? " is" : " are") + " misplaced in the menorah.");
            if (greens == 4) {
                rightOrder = true;
                System.out.println("Congratulations! \nYou look at the menorah, the candles look to be in the right order. You can check that off the list.\n+10 Points!");
                score.solvePuzzle();
                inv.removeItem("candles");
            }
        }
    }

    //Provides information on an item/feature
    public static void lookItem(String itemName, Room currentRoom) {
        if (inv.hasItem(itemName) != -1) { //If the item/feature is in the inventory, aka it's an item:
            if (inv.descriptions[inv.hasItem(itemName)] != null) {
                System.out.println(inv.descriptions[inv.hasItem(itemName)]);
            } else {
                System.out.println("You can't exactly describe the item, but it's in your pocket.");
            }
        } else { //If it's a feature the player wants to look at
            boolean found = false;
            for (Item item : currentRoom.getItems()) {
                if (item != null && item.getName().equalsIgnoreCase(itemName)) {
                    found = true;
                    System.out.println(item.getDescription());
                }
            }

            if (!found) {
                System.out.println("You can't seem to see that item.");
            }
        }
    }
}