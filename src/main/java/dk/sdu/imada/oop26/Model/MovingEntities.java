package main.java.dk.sdu.imada.oop26.Model;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.Tile;

public abstract class MovingEntities {
    protected double x, y, speed;
    Direction direction = Direction.NONE;

    public void update(double dt, Maze maze) {
        // Collision check her?
        // Reference til maze?
        double newX = x + speed * dt * direction.dx;
        double newY = y + speed * dt * direction.dy;

        if(maze.getTile(newX, newY) != Tile.Wall){
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

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
