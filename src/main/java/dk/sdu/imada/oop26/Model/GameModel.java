package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private GameState state = GameState.NORMAL;
    private double powerDuration = 0;

    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;
    private int score;

    public GameModel() {
        pacman = new Pacman();

        ghosts = new ArrayList<Ghost>(4);
        ghosts.add(new Inky());
        ghosts.add(new Pinky());
        ghosts.add(new Clyde());
        ghosts.add(new Blinky());

        maze = new Maze();
    }

    public void update(double dt) {
        if (state == GameState.POWER) {
            powerDuration -= dt;
            if (powerDuration < 0) {
                state = GameState.NORMAL;
            }
        }

        pacman.update(dt, maze);

        double x = pacman.getx() + 0.5;
        double y = pacman.gety() + 0.5;

        if (maze.getTile(x, y) == Tile.Pellet) {
            maze.emptyTile(x, y);
            score++;
        }
        else if (maze.getTile(x, y) == Tile.PowerPellet) {
            score++;
            maze.emptyTile(x, y);
            
            state = GameState.POWER;
            powerDuration = 10;
        }

        for (Ghost ghost : ghosts) {
            ghost.update_direction(pacman, maze);
            ghost.update(dt, maze);
        }
    }

    public GameState getState() {
        return state;
    }

    public double getPowerDuration() {
        return powerDuration;
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public double getx() {
        return pacman.getx();
    }

    public double gety() {
        return pacman.gety();
    }

    public int getScore() {
        return score;
    }

    public Direction getDirection() {
        return pacman.getDirection();
    }

    public void setDirection(Direction direction) {
        pacman.setWantedDirection(direction);
    }
}
