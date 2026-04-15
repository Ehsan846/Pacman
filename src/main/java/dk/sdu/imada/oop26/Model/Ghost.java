package dk.sdu.imada.oop26.Model;

public abstract class Ghost extends MovingEntity {
    protected boolean alive = true;
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

    public void goto_target(double targetX, double targetY, Maze maze) {
        Direction opposite = Direction.oppositeDirection(getDirection());
        double minDist = Double.POSITIVE_INFINITY;

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
                setWantedDirection(d);
            }
        }
    }

    public GhostType gettype() {
        return type;
    }

    public boolean isAlive() {
        return alive;
    }
}
