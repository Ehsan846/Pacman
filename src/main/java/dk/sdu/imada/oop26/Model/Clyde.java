package dk.sdu.imada.oop26.Model;

public class Clyde extends Ghost {
    public Clyde() {
        type = GhostType.CLYDE;
        position = new Position(15.5, 14);
    }

    @Override
    public Position scatter(Maze maze) {
        return new Position(1, Maze.MAZE_HEIGHT-2);
    }

    @Override
    public Position target_pacman(Pacman pacman, Maze maze) {
        double dx = pacman.getPostition().x() - position.x();
        double dy = pacman.getPostition().y() - position.y();
        double dist = Math.sqrt(dx*dx+dy*dy);

        if (dist <= 8) {
            return new Position(1, 1);
        } else  {
            return pacman.getPostition();
        }
    }
}
