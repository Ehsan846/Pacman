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
        ghosts.add(new Binky());
        ghosts.add(new Clyde());
        ghosts.add(new Pinky());
    }

    public void update(double dt) {
        pacman.update(dt);

        for (Ghost ghost : ghosts) {
            ghost.update(dt);
        }
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
