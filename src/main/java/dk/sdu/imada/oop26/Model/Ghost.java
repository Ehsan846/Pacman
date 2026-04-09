package dk.sdu.imada.oop26.Model;

public abstract class Ghost extends MovingEntity {
    protected GhostType type;

    public Ghost() {
        speed = 8;
    }

    public abstract void update_direction(Pacman pacman, Maze maze);

    public GhostType gettype() {
        return type;
    }


}
