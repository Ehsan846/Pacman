package dk.sdu.imada.oop26.Model;

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