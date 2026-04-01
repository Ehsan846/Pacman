package dk.sdu.imada.oop26.Model;
package dk.sdu.imada.oop26;

import main.java.dk.sdu.imada.oop26.Model.Maze;
import main.java.dk.sdu.imada.oop26.Model.MovingEntities;
import dk.sdu.imada.oop26.Model.Tile;
import dk.sdu.imada.oop26.Model.Game;

public class Pacman extends MovingEntities {
    public Pacman() {
        x = 100;
        y = 100;
        speed = 125;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}