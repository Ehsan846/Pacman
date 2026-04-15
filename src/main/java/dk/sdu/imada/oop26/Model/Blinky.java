package dk.sdu.imada.oop26.Model;
import java.util.ArrayList;
import java.util.List;

public class Blinky extends Ghost {
    private int last_x;
    private int last_y;
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
        x = 15;
        y = 23;
    }

    public void update_direction(Pacman pacman, Maze maze) {
        if ((int)x  == last_x && (int)y == last_y)
            return;
        goto_target(pacman.x, pacman.y, maze);
        last_x = (int)x;
        last_y = (int)y;
    }
}
