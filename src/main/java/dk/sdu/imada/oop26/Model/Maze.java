package dk.sdu.imada.oop26.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    // hardcoded for now
    public static final int MAZE_WIDTH = 28;
    public static final int MAZE_HEIGHT = 31;

    private Tile[][] tiles = new Tile[MAZE_WIDTH][MAZE_HEIGHT];

    private Position ghostRespawn;

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
                        ghostRespawn = new Position(x, y);
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

    public Tile getTile(Position p) {
        if (p.x() < 0 || p.x() >= MAZE_WIDTH || p.y() < 0 || p.y() >= MAZE_HEIGHT)
            return Tile.Empty;
        return tiles[(int)p.x()][(int)p.y()];
    }


    public void emptyTile(Position p) {
        if (p.x() < 0 || p.x() > MAZE_WIDTH - 1 || p.y() < 0 || p.y() > MAZE_HEIGHT - 1)
            return;
        tiles[(int)p.x()][(int)p.y()] = Tile.Empty;
    }

    public boolean inGhostSlowZone(Position p) {
        return (
            p.y() == 14 && (p.x() <= 6 || p.x() >= Maze.MAZE_WIDTH-6) // Portal area
            || getTile(p) == Tile.Gate // Ghost entrance
        );
    }

    public boolean inPrison(Position p) {
        return p.x() >= 10 && p.x() <= 17 && p.y() >= 12 && p.y() <= 16;
    }

    public Position getRespawn() {
        return ghostRespawn;
    }

    public boolean allPelletsEaten(){
        for (int i = 0; i < MAZE_WIDTH ; i++) {
            for (int j = 0; j < MAZE_HEIGHT; j++) {
                Tile t = getTile(new Position(i, j));
                if (t == Tile.Pellet || t == Tile.PowerPellet) {
                    return false;
                }
            }
        }
        return true;
    }
}
