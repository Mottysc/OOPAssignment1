package org.uob.a1;

public class Game {
    
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        Map map = new Map(6, 5);

        //Creating the areas
        Room kitchen = new Room("Kitchen", "", 'k', new Position(1, 0));
        map.placeRoom(kitchen.getPosition(), kitchen.getSymbol());
        Room livingRoom = new Room("Living Room", "", 'l', new Position(1, 1));
        map.placeRoom(livingRoom.getPosition(), livingRoom.getSymbol());
        Room hallway = new Room("Hallway", "", 'h', new Position(2, 1));
        map.placeRoom(hallway.getPosition(), hallway.getSymbol());
        Room grandma = new Room("Grandma's House", "", 'g', new Position(4, 3));
        map.placeRoom(grandma.getPosition(), grandma.getSymbol());
        Room shop = new Room("Shop", "", 's', new Position(0, 4));
        map.placeRoom(shop.getPosition(), shop.getSymbol());
        Room neighbour = new Room("Neighbour's House", "", 'n', new Position(5, 4));
        map.placeRoom(neighbour.getPosition(), neighbour.getSymbol());
        Room diningRoom = new Room("Dining Room", "", 'd', new Position(3, 1));
        map.placeRoom(diningRoom.getPosition(), diningRoom.getSymbol());
        Room attic = new Room("Attic", "", 'a', new Position(3, 0));
        map.placeRoom(attic.getPosition(), attic.getSymbol());
        Room westRoad = new Room("Road", "", 'r', new Position(1, 4));
        map.placeRoom(westRoad.getPosition(), westRoad.getSymbol());
        Room eastRoad = new Room("Road", "", 'r', new Position(4, 4));
        map.placeRoom(eastRoad.getPosition(), eastRoad.getSymbol());
        Room northRoad = new Room("Road", "", 'r', new Position(2, 2));
        map.placeRoom(northRoad.getPosition(), northRoad.getSymbol());
        map.placeRoom(new Position(2, 3), 'r');
        map.placeRoom(new Position(2, 4), 'r');
        map.placeRoom(new Position(3, 4), 'r');


    }
    
}