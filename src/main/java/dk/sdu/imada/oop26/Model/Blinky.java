package dk.sdu.imada.oop26.Model;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        position = new Position(13.5, 11);
    }

    @Override
    public Position scatter(Maze maze) {
        return new Position(Maze.MAZE_WIDTH-2, 1);
    }

    @Override
    public Position target_pacman(Pacman pacman, Maze maze) {
        return pacman.getPostition();
    }
}
