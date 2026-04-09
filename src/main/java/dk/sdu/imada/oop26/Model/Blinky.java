package dk.sdu.imada.oop26.Model;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }
    public void update_direction(Pacman pacman, Maze maze) {
        double xdiff = x - pacman.x;
        double ydiff = y - pacman.y;
        if (Math.abs(xdiff) > Math.abs(ydiff)) {
            if (xdiff < 0) {
                setWantedDirection(Direction.RIGHT);
            } else if (xdiff > 0) {
                setWantedDirection(Direction.LEFT);
            }
        } else {
            if (ydiff < 0) {
                setWantedDirection(Direction.DOWN);
            } else if (ydiff > 0) {
                setWantedDirection(Direction.UP);
            }
        }
    }

}
