package dk.sdu.imada.oop26.Model;


public abstract class Ghost extends MovingEntity {
    protected GhostState state = GhostState.ALIVE;
    protected GhostType type;

    private int last_x;
    private int last_y;
    private int last_px;
    private int last_py;

    public static final int DEFAULT_SPEED = 8;
    public static final int SLOWED_SPEED = 4;
    public static final int DEAD_SPEED = 12;

    public Ghost() {
        speed = DEFAULT_SPEED;
    }

    private void set_speed(Maze maze) {
        if (isAlive() && maze.inGhostSlowZone(x, y)) {
            speed = SLOWED_SPEED;
        } else if (state == GhostState.ALIVE) {
            speed = DEFAULT_SPEED;
        } else if (state == GhostState.SCARED) {
            speed = SLOWED_SPEED;
        } else if (state == GhostState.DEAD) {
            speed = DEAD_SPEED;
        }
    }

    @Override
    public void update(double dt, Maze maze) {
        set_speed(maze);
        super.update(dt, maze);
    }

    public void gotoRespawn(Maze maze) {
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, maze.getRespawnX(), maze.getRespawnY(), getDirection()));
    }

    abstract public void scatter(Maze maze);

    abstract public void target_pacman(Pacman pacman, Maze maze);

    public void update_direction(Pacman pacman, Maze maze) {
        switch (state) {
            case ALIVE:
                target_pacman(pacman, maze);
                break;
            case DEAD:
                gotoRespawn(maze);
                break;
            case SCARED:
                scatter(maze);
                break;
        }
    }


    public void update(Pacman pacman, Maze maze) {
        if ((int)x == last_x && (int)y == last_y && (int)pacman.x == last_px && pacman.y == last_py)
            return;
        update_direction(pacman, maze);
        last_x = (int)x;
        last_y = (int)y;
        last_px = (int)pacman.x;
        last_py = (int)pacman.y;
    }

    public void eat() {
        state = GhostState.DEAD;
    }

    public void normal() {
        state = GhostState.ALIVE;
    }

    public void scared() {
        state = GhostState.SCARED;
    }

    public GhostType gettype() {
        return type;
    }

    public boolean isAlive() {
        return state != GhostState.DEAD;
    }
}
