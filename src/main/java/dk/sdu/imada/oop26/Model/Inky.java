package dk.sdu.imada.oop26.Model;

public class Inky extends Ghost {
    private int last_x;
    private int last_y;
    private int last_px;
    private int last_py;
    private Blinky blinky;

    public Inky(Blinky blinky) {
        this.blinky = blinky;
        type = GhostType.INKY;
        x = 11.5;
        y = 14;
    }

    @Override
    public void update_direction(Pacman pacman, Maze maze) {
        if ((int)x == last_x && (int)y == last_y && (int)pacman.x == last_px && (int)pacman.y == last_py) {
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
        finalVectorX = Math.max(0, Math.min(maze.getWidth()-1, finalVectorX));
        finalVectorY = Math.max(0, Math.min(maze.getHeight()-1, finalVectorY));

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

        setWantedDirection(AStar.astar(maze, (int)x, (int)y, finalVectorX, finalVectorY));

	
        //Problem: it gets stuck for some reason,
        //Cause: I suspect that its detecting walls. 
        //Solution: implement behaviour for when detecting wall

        last_x = (int)x;
        last_y = (int)y;
        last_px = (int)pacman.x;
        last_py = (int)pacman.y;
    }
}
