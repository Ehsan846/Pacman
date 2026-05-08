package dk.sdu.imada.oop26.Model;


public abstract class Ghost extends MovingEntity {
    protected GhostState state = GhostState.ALIVE;
    protected GhostType type;

    private Position lastpos = new Position(0, 0);
    private Position lastPacman = new Position(0, 0);

    public static final int DEFAULT_SPEED = 8;
    public static final int SLOWED_SPEED = 4;
    public static final int DEAD_SPEED = 12;

    public Ghost() {
        speed = DEFAULT_SPEED;
    }

    private void set_speed(Maze maze) {
        if (isAlive() && maze.inGhostSlowZone(position)) {
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

    public Position gotoRespawn(Maze maze) {
        return maze.getRespawn();
    }

    abstract public Position scatter(Maze maze);

    abstract public Position target_pacman(Pacman pacman, Maze maze);


    public void update_direction(Pacman pacman, Maze maze) {
//        if (lastpos.equals(Position.floorPosition(position)) && lastPacman == Position.floorPosition(pacman.getPostition()))
//            return;

        Position target = new Position(0, 0);
        switch (state) {
            case ALIVE:
                target = target_pacman(pacman, maze);
                break;
            case DEAD:
                target = gotoRespawn(maze);
                break;
            case SCARED:
                target = scatter(maze);
                break;
        }

        setWantedDirection(AStar.astar(maze, Position.floorPosition(position), Position.floorPosition(target), getDirection()));

        lastpos = Position.floorPosition(position);
        lastPacman = Position.floorPosition(pacman.getPostition());
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

    public GhostState getState() {
        return state;
    }
}
