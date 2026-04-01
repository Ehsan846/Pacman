package main.java.dk.sdu.imada.oop26.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    // hardcoded for now
    public static final int MAZE_WIDTH = 28;
    public static final int MAZE_HEIGHT = 31;

    private Tile[][] tiles = new Tile[MAZE_WIDTH][MAZE_HEIGHT];

    Maze() {
        try {
            readFile("map.txt");
        } catch (IOException e) {
            System.out.println("error opening map");
            System.exit(1);
        }

    }
    private void readFile(String filename) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filename));

        String line;
        for (int y = 0; y < MAZE_HEIGHT; y++) {
            line = reader.readLine();
            for (int x = 0; x < MAZE_WIDTH; x++) {
                char cell = line.charAt(x);
                switch (cell) {
                    case '#':
                        tiles[x][y] = Tile.Wall;
                        break;
                    case '.':
                        tiles[x][y] = Tile.Pellet;
                        break;
                    case 'o':
                        tiles[x][y] = Tile.PowerPellet;
                        break;
                    case ' ':
                        tiles[x][y] = Tile.Empty;
                        break;
                }
            }
        }
    }

    public Tile getTile(double x, double y) {
        return tiles[x][y];
    }


    public void emptyTile(double x, double y) {
        tiles[x][y] = Tile.Empty;
    }
    
}
