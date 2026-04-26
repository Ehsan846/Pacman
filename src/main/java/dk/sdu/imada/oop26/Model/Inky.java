package dk.sdu.imada.oop26.Model;
        //Problem: gets stuck in some corners? Also moves in the oppisite direction without hitting the wall.

public class Inky extends Ghost {
    private Blinky blinky;

    public Inky(Blinky blinky) {
        this.blinky = blinky;
        type = GhostType.INKY;
        x = 11.5;
        y = 14;
    }

    @Override
    public void update_direction(Pacman pacman, Maze maze) {
        if (!alive) {
            super.update_direction(pacman, maze);
            return;
        }

        int blinkyX = (int)blinky.getx();
        int blinkyY = (int)blinky.gety();

        Direction pacDir = pacman.getDirection();
        int aheadX = (int)pacman.getx() + 2 * pacDir.dx;
        int aheadY = (int)pacman.gety() + 2 * pacDir.dy;

        int vectorX = aheadX - blinkyX;
        int vectorY = aheadY - blinkyY;

        int finalVectorX = blinkyX + vectorX * 2;
        int finalVectorY = blinkyY + vectorY * 2;

        // stay inside the map
        finalVectorX = Math.max(0, Math.min(maze.MAZE_WIDTH-1, finalVectorX));
        finalVectorY = Math.max(0, Math.min(maze.MAZE_HEIGHT-1, finalVectorY));

        // Find nearest non-wall tile to target
        while (maze.getTile(finalVectorX, finalVectorY) == Tile.Wall) {
            int dx = finalVectorX - (int)x;
            int dy = finalVectorY - (int)y;
            if (Math.abs(dx) > Math.abs(dy)) {
                finalVectorX -= Integer.signum(dx);
            } else {
                finalVectorY -= Integer.signum(dy);
            }
        }

        setWantedDirection(AStar.astar(maze, (int)x, (int)y, finalVectorX, finalVectorY, getDirection()));
    }
}
