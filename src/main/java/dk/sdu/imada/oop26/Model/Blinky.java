package dk.sdu.imada.oop26.Model;
import java.util.ArrayList;
import java.util.List;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
        x = 15;
        y = 23;
    }

    public void update_direction(Pacman pacman, Maze maze) {
        Direction opposite = Direction.oppositeDirection(getDirection());
        double minDist = Double.POSITIVE_INFINITY;

        for (Direction d : Direction.values()) {
            if (d == Direction.NONE)
                continue;
            if (d == opposite)
                continue;
            double newx = Math.floor(x) + d.dx;
            double newy = Math.floor(y) + d.dy;

            if (maze.getTile(newx, newy) != Tile.Wall) {
                double xdiff = newx - pacman.x;
                double ydiff = newy - pacman.y;
                double dist = xdiff*xdiff + ydiff*ydiff;

                if (dist < minDist) {
                    minDist = dist;
                    setWantedDirection(d);
                }
            }
        }
    }
}
