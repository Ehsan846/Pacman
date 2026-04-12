package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }

    public void update_direction(Pacman pacman, Maze maze) {
        double xdiff = x - pacman.x;
        double ydiff = y - pacman.y;

        List<Direction> priority = new ArrayList<>();

        if (Math.abs(xdiff) > Math.abs(ydiff)) {
            priority.add(xdiff < 0 ? Direction.RIGHT : Direction.LEFT);
            priority.add(ydiff < 0 ? Direction.DOWN : Direction.UP);
            priority.add(ydiff < 0 ? Direction.UP : Direction.DOWN);
            priority.add(xdiff < 0 ? Direction.LEFT : Direction.RIGHT);
        } else {
            priority.add(ydiff < 0 ? Direction.DOWN : Direction.UP);
            priority.add(xdiff < 0 ? Direction.RIGHT : Direction.LEFT);
            priority.add(xdiff < 0 ? Direction.LEFT : Direction.RIGHT);
            priority.add(ydiff < 0 ? Direction.UP : Direction.DOWN);
        }

        for (Direction d : priority) {
            if (canMove(d, maze)) {
                setWantedDirection(d);
                break;
            }
        }
    }

}
