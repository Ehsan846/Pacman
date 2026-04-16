package dk.sdu.imada.oop26.Model;

public class Blinky extends Ghost {
    private int last_x;
    private int last_y;
    private int last_px;
    private int last_py;
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }

    public void update_direction(Pacman pacman, Maze maze) {
        if ((int)x == last_x && (int)y == last_y && (int)pacman.x == last_px && pacman.y == last_py)
            return;
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y));
        last_x = (int)x;
        last_y = (int)y;
        last_px = (int)pacman.x;
        last_py = (int)pacman.y;
    }
}
