package dk.sdu.imada.oop26.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    // hardcoded for now
    public static final int MAZE_WIDTH = 28;
    public static final int MAZE_HEIGHT = 31;

    private Tile[][] tiles = new Tile[MAZE_WIDTH][MAZE_HEIGHT];

    private int ghostRespawnX = 0;
    private int ghostRespawnY = 0;

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
                    case 'r':
                        tiles[x][y] = Tile.GhostRespawn;
                        ghostRespawnX = x;
                        ghostRespawnY = y;
                        break;
                    default:
                    case ' ':
                        tiles[x][y] = Tile.Empty;
                        break;
                    case '_':
                        tiles[x][y] = Tile.Gate;
                        break;
                }
            }
        }
        reader.close();
    }

    public Tile getTile(double doubleX, double doubleY) {
        final int x = (int)Math.round(doubleX);
        final int y = (int)Math.round(doubleY);
        if (x < 0 || x > MAZE_WIDTH - 1 || y < 0 || y > MAZE_HEIGHT - 1)
            return Tile.Empty;
        return tiles[x][y];
    }


    public void emptyTile(double doubleX, double doubleY) {
        final int x = (int)Math.round(doubleX);
        final int y = (int)Math.round(doubleY);
        if (x < 0 || x > MAZE_WIDTH - 1 || y < 0 || y > MAZE_HEIGHT - 1)
            return;
        tiles[x][y] = Tile.Empty;
    }

    public boolean inGhostSlowZone(double x, double y) {
        return (
            y == 14 && (x <= 6 || x >= Maze.MAZE_WIDTH-6) // Portal area
            || getTile(x, y) == Tile.Gate // Ghost entrance
        );
    }

    public int getRespawnX() {
        return ghostRespawnX;
    }

    public int getRespawnY() {
        return ghostRespawnY;
    }
}
