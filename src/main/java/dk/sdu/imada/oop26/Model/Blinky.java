package dk.sdu.imada.oop26.Model;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }

    public void update_direction(Pacman pacman, Maze maze) {
        if (!alive) {
            super.update_direction(pacman, maze);
            return;
        }
        
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y, getDirection()));
    }
}
