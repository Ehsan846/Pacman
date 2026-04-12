package dk.sdu.imada.oop26.Model;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.Tile;

public abstract class MovingEntity {
    protected double x, y, speed;
    private Direction direction = Direction.NONE;
    private Direction wantedDirection = direction;

    public void update(double dt, Maze maze) {
        if (move(wantedDirection, dt, maze)) {
            direction = wantedDirection;
        } else {
            move(direction, dt, maze);
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

        if (maze.getTile(newX, newY) != Tile.Wall &&
            maze.getTile(newX+1-1e-5, newY) != Tile.Wall &&
            maze.getTile(newX, newY+1-1e-5) != Tile.Wall &&
            maze.getTile(newX+1-1e-5, newY+1-1e-5) != Tile.Wall) {
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }

protected boolean canMove(Direction direction, Maze maze, double dt) {
    double size = speed * dt; // = 0.128
    double checkX = Math.round(x) + size * direction.dx;
    double checkY = Math.round(y) + size * direction.dy;
    return maze.getTile(checkX, checkY) != Tile.Wall &&
           maze.getTile(checkX + size, checkY) != Tile.Wall &&
           maze.getTile(checkX, checkY + size) != Tile.Wall &&
           maze.getTile(checkX + size, checkY + size) != Tile.Wall;
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
        wantedDirection = direction;
    }


    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
