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
        ghosts.add(new Pinky());
        ghosts.add(new Clyde());

    	Blinky blinky = new Blinky();
        ghosts.add(blinky);

        ghosts.add(new Inky(blinky));

        maze = new Maze();
    }

    public void update(double dt) {
        if (state == GameState.POWER) {
            powerDuration -= dt;
            if (powerDuration < 0) {
                state = GameState.NORMAL;
            }
        } else if (state == GameState.FINISHED) {
            return;
        }

        pacman.update(dt, maze);

        final double pacmanX = pacman.getx();
        final double pacmanY = pacman.gety();

        if (maze.getTile(pacmanX, pacmanY) == Tile.Pellet) {
            maze.emptyTile(pacmanX, pacmanY);
            score++;
        }
        else if (maze.getTile(pacmanX, pacmanY) == Tile.PowerPellet) {
            score++;
            maze.emptyTile(pacmanX, pacmanY);

            state = GameState.POWER;
            powerDuration = 10;
        }

        for (Ghost ghost : ghosts) {
            ghost.update(pacman, maze);
            ghost.update(dt, maze);

            final double ghostX = ghost.getx();
            final double ghostY = ghost.gety();

            if (ghost.isAlive()) {
                if (pacmanX < ghostX + 1 && pacmanX > ghostX - 1
                    && pacmanY < ghostY + 1 && pacmanY > ghostY - 1) {
                    if (state == GameState.POWER) {
                        ghost.eat();
                    } else {
                        // -1 hp, hp = 0 => state = GameState.FINISHED;
                    }
                }
            } else {
                if (maze.getTile(ghostX, ghostY) == Tile.GhostRespawn) {
                    ghost.respawn();
                }
            }
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
