package dk.sdu.imada.oop26.Model;


public class Pacman extends MovingEntities {
    public Pacman() {
        this.x = 100;
        this.y = 100;
        this.speed = 125;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
