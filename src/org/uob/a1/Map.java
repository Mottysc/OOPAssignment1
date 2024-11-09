package org.uob.a1;

public class Map {
    final private char EMPTY = '.';
    public int width;
    public int height;
    public char[][] map;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = EMPTY;
            }
        }
    }

    public void placeRoom(Position pos, char symbol) {
        map[pos.y][pos.x] = symbol;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}