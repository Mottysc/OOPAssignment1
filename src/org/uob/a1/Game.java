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
    static Person[] people;
    static boolean wearingBoots = false;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        //Creating the areas
        Room kitchen = new Room("the Kitchen", "You notice a hastily-written ToDo list stuck on the fridge. \nLooking out of the window, you can see the snowfall picking up. It's going to be a cold night. \nTo the south you see your living room.", 'k', new Position(1, 0));
        map.placeRoom(kitchen.getPosition(), kitchen.getSymbol());
        Room livingRoom = new Room("the Living Room", "You look around and see your menorah near the window with drips of wax from years gone by, next to it are the candles in a neat pile waiting to be lit. \nYour coffee table is adorned with a bowl full of gelt.\nTo your east is the hallway, and to the north is the kitchen.", 'l', new Position(1, 1));
        map.placeRoom(livingRoom.getPosition(), livingRoom.getSymbol());
        Room hallway = new Room("the Hallway", "A cluttered hallway lays before you. Your family's coats and scarves are hung up waiting to be used. \nYou can see your boots laying on the floor, still covered with bits of snow from earlier in the day. \nYour front door is to the south of you, your living room to the west, and the dining room in the east.", 'h', new Position(2, 1));
        map.placeRoom(hallway.getPosition(), hallway.getSymbol());
        Room grandmaHouse = new Room("Grandma's House", "Grandma’s house is warm and inviting, with the soft glow of the fireplace and the scent of fresh cookies in the air. \nGrandma sits in her favorite chair, knitting with a gentle smile, surrounded by cozy blankets and an old photo on the wall.", 'g', new Position(5, 4));
        map.placeRoom(grandmaHouse.getPosition(), grandmaHouse.getSymbol());
        Room shop = new Room("the Shop", "The shop is small but cozy, with shelves lined with potatoes, bottles of oil, bags of flour, some sweets, and some other tasty goods.\nThe warm light spills from the window, casting a friendly glow over the space.\nBehind the counter, a kind-looking shopkeeper greets you with a smile, her eyes friendly and welcoming as she tidies the cluttered shelves.", 's', new Position(0, 4));
        map.placeRoom(shop.getPosition(), shop.getSymbol());
        Room neighbour = new Room("the Neighbour's House", "The neighbor’s house is quiet, with dim lights glowing through the curtains. Inside, a young boy sits by the window, looking out with a sad expression on his face. You have an urge to ask him what's wrong.", 'n', new Position(3, 3));
        map.placeRoom(neighbour.getPosition(), neighbour.getSymbol());
        Room diningRoom = new Room("the Dining Room", "The table is laid, ready for a nice meal. You see your mother sitting near the table, relaxing. You notice a small gift box next to her.", 'd', new Position(3, 1));
        map.placeRoom(diningRoom.getPosition(), diningRoom.getSymbol());
        Room attic = new Room("the Attic", "The attic is dim and dusty, filled with storage boxes of decorations and childhood memories. A small window lets in a sliver of moonlight, casting shadows over the cluttered space.", 'a', new Position(3, 0));
        map.placeRoom(attic.getPosition(), attic.getSymbol());
        Room westRoad = new Room("the Road","The road is quiet, lit by street lamps casting soft glows on the snowy path.\nTo the east, the road forks toward your house and the nearby homes, while to the west, the shop stands with its frosted windows and wooden sign gently swaying in the cold breeze.", 'r', new Position(1, 4));
        map.placeRoom(westRoad.getPosition(), westRoad.getSymbol());
        Room eastRoad = new Room("the Road", "A gentle glow comes from Grandma's house to the east, a nice contrast to the dark road. The path back to the fork lies westward, while the faint sounds of nearby houses blend into the still night.", 'r', new Position(4, 4));
        map.placeRoom(eastRoad.getPosition(), eastRoad.getSymbol());
        Room northRoad = new Room("the Road", "You see your house to the north. The lights in the living room and dining room shining in the dark of the night. The road is quiet and deserted, only lit by the street lamps from the road to the south.", 'r', new Position(2, 2));
        map.placeRoom(northRoad.getPosition(), northRoad.getSymbol());
        Room northMidRoad = new Room("the Road", "As you continue down the road, you see the houses with the warm glows of Christmas trees and Menorahs.\nThe snow continues fall as you walk, crunching underneath your steps. You see your house further north and the the corner road on your south.", 'r', new Position(2, 3));
        map.placeRoom(northMidRoad.getPosition(), northMidRoad.getSymbol());
        Room splitRoad = new Room("the corner Road", "The road is at a fork. You can walk north to go to your house, east to get to your grandma's house or your neighbour's house, or you can go west to the shop.", 'r', new Position(2, 4));
        map.placeRoom(splitRoad.getPosition(), splitRoad.getSymbol());
        Room eastMidRoad = new Room("the Road", "You continue down the road. To your north is your neighbour's house, further east is your grandma's house, further west is the corner road.", 'r', new Position(3, 4));
        map.placeRoom(eastMidRoad.getPosition(), eastMidRoad.getSymbol());

        rooms = new Room[]{kitchen, livingRoom, diningRoom, attic, westRoad, eastRoad, northRoad, grandmaHouse, neighbour, hallway, shop, northMidRoad, splitRoad,eastMidRoad};

        Item todoList = new Item("Todo list", "You read the todo list, it says to go shopping, fry latkes, prepare the menorah, and put up the decorations. \nYou should probably help your mother and do some of these.");
        Item pan = new Item("Pan", "The frying pan is on the counter, ready to be used to make latkes.");
        Item menorah = new Item("Menorah", "It's your childhood menorah, you can see the signs of time through the little dents and scratches. Next to it is an assortment of candles neatly prepared.");
        Item candles = new Item("Candles", "The candles you need to light the menorah. There are some differently coloured ones, but you can't remember in which order you like to place them.", true);
        Item boots = new Item("Boots", "Your favourite pair of boots. These will help you stay warm outside if used.", true);
        Item gelt = new Item("Gelt", "These are Hanukkah Gelt, delicious golden chocolate coins that remind you of your childhood.", true);
        Item gift = new Item("Gift", "It's a gift for grandma. I should probably go give it to her.", true);
        Item potatoes = new Item("Potatoes", "It's a sack of potatoes, you need these to make your latkes for your mum.", true);
        Item oil = new Item("Oil", "It's a bottle of oil, you need these to make sure latkes.", true);
        Item flour = new Item("Flour", "A packet of flour. You don't need these, so you'll be honest and won't take them.");
        Item sweets = new Item("Sweets", "It's some tasty-looking sour sweets. Your mum won't be happy if you come home with these...");
        Item oldBox = new Item("Box", "You look into the old box, inside it you can see the decorations that haven't been put up yet along with some keepsakes.");
        Item decorations = new Item("Decorations", "It's the decorations you need to put up outside your house. Your mother would be really appreciative if you did this.", true);
        Item keepsakes = new Item("Keepsakes", "It's some old keepsakes that your mother has kept for a few years. They look delicate.");
        Item photo = new Item("Photo", "It's an old family photo your grandma loves. On it you can see your family; your uncle Richard, your aunt Barbra, your Grandma, and your dad Patrick.");
        kitchen.setItems(new Item[]{todoList, pan});
        livingRoom.setItems(new Item[]{candles, menorah, gelt});
        hallway.setItems(new Item[]{boots});
        shop.setItems(new Item[]{potatoes, flour, sweets, oil});
        diningRoom.setItems(new Item[]{gift});
        attic.setItems(new Item[]{oldBox, decorations, keepsakes});

        Person mother = new Person("Your mother", diningRoom, "Please make sure you finish all the items on your ToDo list that I left on the fridge. Also, go give your grandma a visit, she would appreciate your presence.");
        Person grandma = new Person("Grandma", grandmaHouse, "Hey! I'm so glad to decided to come visit me! ");
        Person neighbourKid = new Person("Kid", neighbour, "Hey neighbour! I don't have anyone to play dreidel with, could you please play with me? Use your gelt and I'll owe you one if you win.");
        Person shopkeeper = new Person("Shopkeeper", shop, "Hey, welcome to the shop. As it's the season of giving, everything will be free tonight, take whatever you need. Happy holidays!");
        people = new Person[]{mother, grandma, neighbourKid, shopkeeper};

        currentRoom = kitchen;
        System.out.print("You enter " + currentRoom.getName() + ". ");
        System.out.println(currentRoom.getDescription());

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
        //see if an item/feature/direction was specified
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
                                if (currentRoom == rooms[9]){
                                    if (wearingBoots) {
                                        player.y += 1;
                                    }
                                    else {
                                        System.out.println("It's getting cold outside, you should find your boots and put them on before leaving.");
                                    }
                                }
                                else {
                                    player.y += 1;
                                }
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
                            System.out.println("You enter " + currentRoom.getName() + ". ");
                            score.visitRoom();
                            //System.out.println(currentRoom.getDescription());
                        }
                        break;
                    }
                }
                break;

            case "map":
                System.out.println(map.display());
                break;

            case "talk":
                if (!hasFeature){
                    System.out.println("You mutter something to yourself");
                }
                else {
                    switch (feature) {
                        case "mum", "mother", "your mother", "your mum", "to mum":
                            if (currentRoom != people[0].getRoom()){
                                System.out.println("You think you should go find your mother and talk to her. She might be in the dining room");
                            }
                            else {
                                System.out.println(people[0].speak());
                            }
                            break;
                        case "grandma", "grandmother", "your grandma", "your grandmother", "to grandma":
                            if (currentRoom != people[1].getRoom()){
                                System.out.println("You think you should travel to your grandma's house and talk to her.");
                            }
                            else {
                                System.out.print(people[1].speak());
                                if (inv.hasItem("gift") != -1){
                                    System.out.println("Thank you so much for bringing me a gift, I really do appreciate it!\n+10 Points!");
                                    score.solvePuzzle();
                                }
                                else {
                                    System.out.println(" ");
                                }
                            }
                            break;
                        case "kid", "neighbour", "neighbour's kid", "to kid":
                            if (currentRoom != people[2].getRoom()){
                                System.out.println("You think that the neighbour's kid is most likely at their house at this time of the night.");
                            }
                            else {
                                System.out.println(people[2].speak());
                            }
                            break;
                        case "shopkeeper":
                            if (currentRoom != people[3].getRoom()) {
                                System.out.println("You're not sure why you want to talk to the shopkeeper now. You're not even in the shop.");
                            }
                            else{
                                System.out.println(people[3].speak());
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
                }
                else {
                    boolean found = false;
                    for (Item item : currentRoom.getItems()) {
                        if (item != null && feature.equalsIgnoreCase(item.getName())){
                            if (item.canPickUp) {
                                Item[] copyItems = currentRoom.getItems();
                                for (int i = 0; i < copyItems.length; i++) {
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
                            }
                            else {
                                System.out.println("You know you shouldn't pick that up.");
                            }
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("You can't find that item to pick up.");
                    }
                }
                break;

            case "inventory":
                if (inv.isEmpty()){
                    System.out.println("You don't have any items in your inventory.");
                }
                else {
                    System.out.println("You look into your pockets and see: " + inv.displayInventory());
                }
                break;

            case "use":
                if (!hasFeature) {
                    System.out.println("You want to use an item in your inventory, but aren't sure what specifically.");
                }
                else {
                    switch (feature) {
                        case "boots":
                            if (inv.hasItem("boots") != -1){
                                System.out.println("You put on your boots and are now ready to venture outside\n+10 Points!");
                                inv.removeItem("boots");
                                score.solvePuzzle();
                                wearingBoots = true;
                            }
                            else {
                                System.out.println("You don't have your boots in your hands.");
                            }
                            break;
                        case "candles":
                            if (inv.hasItem("candles") != -1) {
                                playMastermind();
                            }
                            else {
                                System.out.println("You can't use the candles if you haven't got them in your inventory.");
                            }
                            break;
                        case "gift":
                            if (inv.hasItem("gift") != -1) {
                                if (currentRoom != rooms[7]) {
                                    System.out.println("You should probably go give your gift to your grandma.");
                                }
                            }
                            else {
                                System.out.println("You don't have any gift on you, maybe there's one somewhere at home.");
                            }
                        case "potatoes":
                            if (inv.hasItem("potatoes") != -1){
                                if (currentRoom != rooms[0]){
                                    System.out.println("You should save the potatoes for when you're in the kitchen and make latkes for your mother.");
                                }
                                else {
                                    if (inv.hasItem("oil") != -1) {
                                        System.out.println("You peel the potatoes, grate them, add oil to the pan, and turn on the stove.\nYou create the latkes patties and carefully place them into the hot oil. \nThey sizzle as they turn a golden-brown colour before you remove them, ready to eat.\nYou have successfully made the latkes.\n+10 Points!");
                                        inv.removeItem("oil");
                                        inv.removeItem("potatoes");
                                        score.solvePuzzle();
                                    }
                                    else {
                                        System.out.println("You should probably hurry back and also get oil from shop to fry the latkes...");
                                    }
                                }
                            }
                            else {
                                System.out.println("You don't have any potatoes on you, you should get some from the shop.");
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
                System.out.println("Goobye.");
                break;

            case "help":
                String helpMessage = """
                        Here are the commands you can use:
                        • "move <direction>" - (<direction> can be "north", "south", "east", "west"). The player moves to a new room based on the direction.
                        • "look" - Displays a description of the room the player is in.
                        • "look <feature>" - Displays a more detailed description of a feature of a room.
                        • "look <item>" - Displays a description of an item in your inventory.
                        • "inventory" - Displays a list of all items the player has obtained.
                        • "score" - Displays your current score.
                        • "talk <person>" - Interact with someone in the same room as you.
                        • "take <item>" - Pick up an item in front of you.
                        • "use <item>" - Use an item from your inventory.
                        • "map" - Displays a text-based map of the current explored game world.
                        • "help" - Displays a help message.
                        • "quit" - Quits the game
                        """;
                System.out.println(helpMessage);
                break;

            default:
                System.out.println("You're not sure what to do right now...");
        }
    }

    public static boolean tryMove(Position currentPosition, int xDelta, int yDelta) {
        return map.map[currentPosition.y + yDelta][currentPosition.x + xDelta] != '.';
    }

    public static void playMastermind(){
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
        boolean rightOrder = false;
        boolean playing = true;
        final String ORDER = "RBGP";
        final String VALID_COLORS = "RGBPYCTWOC";

        while (playing && !rightOrder) {
            boolean validChars = true;
            System.out.print("->> ");
            String guess = input.nextLine().toUpperCase();

            if (guess.equals("GIVE UP")){
                playing = false;
                System.out.println("You decide to stop putting the candles in the menorah for now.");
                break;
            } else if (guess.equals("HINT")){
                System.out.println("""
                        Here's your hint:
                        You notice a piece of paper with the following scribbled on it;
                        "Grandma's photo\"""");
                continue;
            }

            if (guess.length() != 4) {
                System.out.println("You only need four candles.");
                continue;
            }


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

            int CODE_LENGTH = 4;
            int[] matches = new int[4];
            int greens = 0;
            int yellows = 0;

            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == ORDER.charAt(i)) {
                    matches[i] = 1;
                    greens++;
                }
                else {
                    for (int j = 0; j < 4; j++) {
                        if (guess.charAt(j) == ORDER.charAt(i)  &&  matches[j] == 0  &&  i != j) {
                            matches[j] = 2;
                            yellows++;
                        }
                    }
                }
            }

            System.out.println(greens + (greens == 1 ? " is " : " are ") + "in the menorah and seem"+ (greens == 1 ? "s":"") + " in the right place, " + yellows + (yellows == 1 ? " is " : " are ") + "in the menorah but "+(yellows == 1 ? "doesn't":"don't")+" look right to you.");
            if (greens == 4) {
                rightOrder = true;
                System.out.println("Congratulations! \nYou look at the menorah, the candles look to be in the right order. You can check that off the list.\n+10 Points!");
                score.solvePuzzle();
                inv.removeItem("candles");
                }
            }
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
            boolean found = false;
            for (Item item : currentRoom.getItems()){
                if (item != null && item.getName().equalsIgnoreCase(itemName)){
                    System.out.println(item.getDescription());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("You can't seem to see that item.");
            }
        }
    }

}