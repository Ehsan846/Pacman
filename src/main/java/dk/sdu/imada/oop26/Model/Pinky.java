package dk.sdu.imada.oop26.Model;

public class Pinky extends Ghost {

    public Pinky() {
        type = GhostType.PINKY;
        x = 13.5;
        y = 14;
    }

    @Override
    public void update_direction(Pacman pacman, Maze maze) {
        if (!should_update(pacman)) return;

        Direction pacDir = pacman.getDirection();
        int aheadX = (int)pacman.getx() + 4 * pacDir.dx;
        int aheadY = (int)pacman.gety() + 4 * pacDir.dy;

        // stay inside the map
        aheadX = Math.max(0, Math.min(maze.getWidth()-1, aheadX));
        aheadY = Math.max(0, Math.min(maze.getHeight()-1, aheadY));

        // Find nearest non-wall tile to target
        while (maze.getTile(aheadX, aheadY) == Tile.Wall) {
            int dx = aheadX - (int)x;
            int dy = aheadY - (int)y;
            if (Math.abs(dx) > Math.abs(dy)) {
                aheadX -= Integer.signum(dx);
            } else {
                aheadY -= Integer.signum(dy);
            }
        }

        setWantedDirection(AStar.astar(maze, (int)x, (int)y, aheadX, aheadY));
    }
}
