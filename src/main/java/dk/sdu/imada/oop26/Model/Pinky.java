package dk.sdu.imada.oop26.Model;

public class Pinky extends Ghost {

    public Pinky() {
        type = GhostType.PINKY;
        position = new Position(13.5, 14);
    }

    @Override
    public Position scatter(Maze maze) {
        return new Position(1, 1);
    }

    @Override
    public Position target_pacman(Pacman pacman, Maze maze) {
        Direction pacDir = pacman.getDirection();
        int aheadX = (int)pacman.getPostition().x() + 4 * pacDir.dx;
        int aheadY = (int)pacman.getPostition().y() + 4 * pacDir.dy;

        // stay inside the map
        aheadX = Math.max(0, Math.min(maze.MAZE_WIDTH-1, aheadX));
        aheadY = Math.max(0, Math.min(maze.MAZE_HEIGHT-1, aheadY));
        // Find nearest non-wall tile to target
        while (maze.getTile(new Position(aheadX, aheadY)) == Tile.Wall) {
            double dx = aheadX - position.x();
            double dy = aheadY - position.y();
            if (Math.abs(dx) > Math.abs(dy)) {
                aheadX -= Math.signum(dx);
            } else {
                aheadY -= Math.signum(dy);
            }
        }

        return new Position(aheadX, aheadY);
    }
}
