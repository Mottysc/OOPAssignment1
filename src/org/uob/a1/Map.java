package org.uob.a1;

public class Map {
   private int width;
   private int height;
   final private char EMPTY = '.';
   private char[][] map;

   public Map(int width, int height) {
       this.width = width;
       this.height = height;
       this.map = new char[width][height];
       for (int x = 0; x < width; x++) {
           for (int y = 0; y < height; y++) {
               map[x][y] = EMPTY;
           }
       }
   }

   public void placeRoom(Position pos, char symbol){
       map[pos.x][pos.y] = symbol;
   }

   public String display(){
       StringBuilder sb = new StringBuilder();
       for (int x = 0; x < width; x++) {
           for (int y = 0; y < height; y++) {
               sb.append(map[x][y]);
           }
       }
       return sb.toString();
   }

}