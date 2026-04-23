package dk.sdu.imada.oop26.Model;

public class Clyde extends Ghost {
    public Clyde() {
        type = GhostType.CLYDE;
        x = 15.5;
        y = 14;
    }

    @Override
    public void update_direction(Pacman pacman, Maze maze) {
        if (!should_update(pacman)) return;

        int dist = (int)Math.sqrt(Math.pow(pacman.getx() - x, 2) + Math.pow(pacman.gety() - y, 2));

        if(dist <= 8){
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, 1, 1));
        } else  {
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y));
        }
    }
}
