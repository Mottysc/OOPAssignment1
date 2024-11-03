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
       for (int y = 0; y < width; y++) {
           for (int x = 0; x < height; x++) {
               map[x][y] = EMPTY;
           }
       }
   }

   public void placeRoom(Position pos, char symbol){
       map[pos.x][pos.y] = symbol;
   }

   public String display(){
       StringBuilder sb = new StringBuilder();
       for (int y = 0; y < width; y++) {
           for (int x = 0; x < height; x++) {
               sb.append(map[x][y]);
           }
           sb.append("\n");
       }
       return sb.toString();
   }

}