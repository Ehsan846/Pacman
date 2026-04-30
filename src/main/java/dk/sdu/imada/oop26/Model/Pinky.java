package dk.sdu.imada.oop26.Model;

public class Pinky extends Ghost {

    public Pinky() {
        type = GhostType.PINKY;
        x = 13.5;
        y = 14;
    }

    @Override
    public void scatter(Maze maze) {
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, 1, 1, getDirection()));
    }

    @Override
    public void target_pacman(Pacman pacman, Maze maze) {
        Direction pacDir = pacman.getDirection();
        int aheadX = (int)pacman.getx() + 4 * pacDir.dx;
        int aheadY = (int)pacman.gety() + 4 * pacDir.dy;

        // stay inside the map
        aheadX = Math.max(0, Math.min(maze.MAZE_WIDTH-1, aheadX));
        aheadY = Math.max(0, Math.min(maze.MAZE_HEIGHT-1, aheadY));

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

        setWantedDirection(AStar.astar(maze, (int)x, (int)y, aheadX, aheadY, getDirection()));
    }
}
