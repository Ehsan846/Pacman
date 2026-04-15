package dk.sdu.imada.oop26.Model;

public abstract class Ghost extends MovingEntity {
    protected GhostType type;

    public Ghost() {
        speed = 8;
    }

    @Override
    public void update(double dt, Maze maze) {
        if (y == 14 && (x <= 6 || x >= Maze.MAZE_WIDTH-6))
            speed = 4;
        else
            speed = 8;
        super.update(dt, maze);
    }

    public abstract void update_direction(Pacman pacman, Maze maze);

    public GhostType gettype() {
        return type;
    }


}
