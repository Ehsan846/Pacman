package dk.sdu.imada.oop26.Model;
        //Problem: gets stuck in some corners? Also moves in the oppisite direction without hitting the wall.

public class Inky extends Ghost {
    private Blinky blinky;

    public Inky(Blinky blinky) {
        this.blinky = blinky;
        type = GhostType.INKY;
        position = new Position(11.5, 14);
    }

    @Override
    public Position scatter(Maze maze) {
        return new Position(Maze.MAZE_WIDTH-2, Maze.MAZE_HEIGHT-2);
    }

    @Override
    public Position target_pacman(Pacman pacman, Maze maze) {
        int blinkyX = (int)blinky.getPostition().x();
        int blinkyY = (int)blinky.getPostition().y();

        Direction pacDir = pacman.getDirection();
        int aheadX = (int)pacman.getPostition().x() + 2 * pacDir.dx;
        int aheadY = (int)pacman.getPostition().y() + 2 * pacDir.dy;

        int vectorX = aheadX - blinkyX;
        int vectorY = aheadY - blinkyY;

        int finalVectorX = blinkyX + vectorX * 2;
        int finalVectorY = blinkyY + vectorY * 2;

        // stay inside the map
        finalVectorX = Math.max(0, Math.min(maze.MAZE_WIDTH-1, finalVectorX));
        finalVectorY = Math.max(0, Math.min(maze.MAZE_HEIGHT-1, finalVectorY));

        // Find nearest non-wall tile to target
        while (maze.getTile(new Position(finalVectorX, finalVectorY)) == Tile.Wall) {
            double dx = finalVectorX - position.x();
            double dy = finalVectorY - position.y();
            if (Math.abs(dx) > Math.abs(dy)) {
                finalVectorX -= Math.signum(dx);
            } else {
                finalVectorY -= Math.signum(dy);
            }
        }

        return new Position(finalVectorX, finalVectorY);
    }
}
