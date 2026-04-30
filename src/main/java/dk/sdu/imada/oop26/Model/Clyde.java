package dk.sdu.imada.oop26.Model;

public class Clyde extends Ghost {
    public Clyde() {
        type = GhostType.CLYDE;
        x = 15.5;
        y = 14;
    }

    @Override
    public void scatter(Maze maze) {
        setWantedDirection(AStar.astar(maze, (int)x, (int)y, 1, Maze.MAZE_HEIGHT-2, getDirection()));
    }

    @Override
    public void target_pacman(Pacman pacman, Maze maze) {
        double dx = pacman.getx() - x;
        double dy = pacman.gety() - y;
        double dist = Math.sqrt(dx*dx+dy*dy);

        if (dist <= 8) {
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, 1, 1, getDirection()));
        } else  {
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y, getDirection()));
        }
    }
}
