package dk.sdu.imada.oop26.Model;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }

    @Override
    public void scatter(Maze maze) {
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, Maze.MAZE_WIDTH-2, 1, getDirection()));
    }

    @Override
    public void target_pacman(Pacman pacman, Maze maze) {
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y, getDirection()));
    }
}
