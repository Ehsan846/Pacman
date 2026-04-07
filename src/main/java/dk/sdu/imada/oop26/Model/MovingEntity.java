package dk.sdu.imada.oop26.Model;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.Tile;

public abstract class MovingEntity {
    protected double x, y, speed;
    Direction direction = Direction.NONE;

    public void update(double dt, Maze maze) {
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

        if (maze.getTile(newX, newY) != Tile.Wall &&
            maze.getTile(newX+1-1e-5, newY) != Tile.Wall &&
            maze.getTile(newX, newY+1-1e-5) != Tile.Wall &&
            maze.getTile(newX+1-1e-5, newY+1-1e-5) != Tile.Wall) {
            x = newX;
            y = newY;
        }
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
    }


    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
