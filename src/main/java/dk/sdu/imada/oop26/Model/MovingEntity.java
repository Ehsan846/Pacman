package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.Tile;

public abstract class MovingEntity {
    protected double x, y, speed;
    private Direction direction = Direction.NONE;
    private Direction wantedDirection = direction;

    List<Tile> disallowedTiles = new ArrayList<>(List.of(Tile.Wall));

    public void update(double dt, Maze maze) {
        if (move(wantedDirection, dt, maze)) {
            direction = wantedDirection;
        } else {
            if (!move(direction, dt, maze)){
                x = Math.round(x);
                y = Math.round(y);
            }
        }
    }

    private boolean move(Direction direction, double dt, Maze maze) {
        double newX = x + speed * dt * direction.dx;
        double newY = y + speed * dt * direction.dy;

        switch (direction) {
            case UP:
            case DOWN:
                newX = Math.round(newX);
                break;
            case LEFT:
            case RIGHT:
                newY = Math.round(newY);
                break;
        }

        if (newX <= -1)
            newX += Maze.MAZE_WIDTH;
        else if (newX >= Maze.MAZE_WIDTH)
            newX -= Maze.MAZE_WIDTH;

        final double offset = 1 - 1e-5;
        if (!disallowedTiles.contains(maze.getTile(newX, newY)) &&
            !disallowedTiles.contains(maze.getTile(newX + offset, newY)) &&
            !disallowedTiles.contains(maze.getTile(newX, newY + offset)) &&
            !disallowedTiles.contains(maze.getTile(newX + offset, newY + offset))) {
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        wantedDirection = direction;
    }

    public void setWantedDirection(Direction direction) {
        if (direction != Direction.NONE)
            wantedDirection = direction;
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
