package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

import main.java.dk.sdu.imada.oop26.Model.Blinky;
import main.java.dk.sdu.imada.oop26.Model.Clyde;
import main.java.dk.sdu.imada.oop26.Model.Ghost;
import main.java.dk.sdu.imada.oop26.Model.Inky;
import main.java.dk.sdu.imada.oop26.Model.Maze;
import main.java.dk.sdu.imada.oop26.Model.PacmanEvent;
import main.java.dk.sdu.imada.oop26.Model.Pinky;

public class GameModel {
    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;

    public GameModel() {
        pacman = new Pacman(/* reference til maze ? */);

        ghosts = new ArrayList<Ghost>(4);
        ghosts.add(new Inky());
        ghosts.add(new Blinky());
        ghosts.add(new Clyde());
        ghosts.add(new Pinky());

        // testing
        ghosts.get(0).setX(100);
        ghosts.get(0).setY(200);
        ghosts.get(1).setX(300);
        ghosts.get(1).setY(400);
        ghosts.get(2).setX(500);
        ghosts.get(2).setY(600);
        ghosts.get(3).setX(700);
        ghosts.get(3).setY(800);

        maze = new Maze();
    }

    public void update(double dt) {

        PacmanEvent event = pacman.update(dt, maze);
        if (event == PacmanEvent.pacPellet) {
            score += 1;
        } else if (event == PacmanEvent.pacPower) {
            score += 10;
        }

        for (Ghost ghost : ghosts) {
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

    public void setDirection(Direction direction) {
        pacman.setDirection(direction);
    }
}
