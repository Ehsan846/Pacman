package dk.sdu.imada.oop26.Model;


public abstract class Ghost extends MovingEntity {
    protected boolean alive = true;
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

    @Override
    public void update(double dt, Maze maze) {
        if (alive) {
            if (maze.inGhostSlowZone(x, y))
                speed = SLOWED_SPEED;
            else
                speed = DEFAULT_SPEED;
        }
        
        super.update(dt, maze);
    }

    public void update_direction(Pacman pacman, Maze maze) {
        // Default target respawnpoint
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, maze.getRespawnX(), maze.getRespawnY(), getDirection()));
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

    public Direction goto_target(double targetX, double targetY, Maze maze) {
        /*
        
        
        Bliver den stadig brugt?
        
        
        */
        Direction opposite = Direction.oppositeDirection(getDirection());
        double minDist = Double.POSITIVE_INFINITY;

        Direction r = Direction.NONE;
        for (Direction d : Direction.values()) {
            if (d == Direction.NONE)
                continue;
            if (d == opposite)
                continue;
            double newx = Math.floor(x) + d.dx;
            double newy = Math.floor(y) + d.dy;
            if (maze.getTile(newx, newy) == Tile.Wall)
                continue;

            double xdiff = newx - targetX;
            double ydiff = newy - targetY;
            double dist = xdiff*xdiff + ydiff*ydiff;
            if (dist < minDist) {
                minDist = dist;
                r = d;
            }
        }

        return r;
    }

    public void eat() {
        alive = false;
        speed = DEAD_SPEED;
    }

    public void respawn() {
        alive = true;
        speed = DEFAULT_SPEED;
    }

    public GhostType gettype() {
        return type;
    }

    public boolean isAlive() {
        return alive;
    }
}
