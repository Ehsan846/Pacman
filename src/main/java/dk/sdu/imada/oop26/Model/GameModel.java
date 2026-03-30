package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Pacman pacman;
    private List<Ghost> ghosts;

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
    }

    public void update(double dt) {
        pacman.update(dt);

        for (Ghost ghost : ghosts) {
            ghost.update(dt);
        }
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
