package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
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
            // Powerup
        }



        for (Ghost ghost : ghosts) {
            ghost.update_direction(pacman, maze);
            ghost.update(dt, maze);
        }
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Ghost> getghosts() {
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
