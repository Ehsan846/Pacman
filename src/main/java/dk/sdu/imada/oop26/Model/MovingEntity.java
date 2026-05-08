package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.AbstractMap.SimpleEntry;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.Tile;

public abstract class MovingEntity {
    protected double speed;
    protected Position position;
    private Direction direction = Direction.NONE;
    private Direction wantedDirection = direction;

    List<Tile> disallowedTiles = new ArrayList<>(List.of(Tile.Wall));

    public void update(double dt, Maze maze) {
        if (move(wantedDirection, dt, maze)) {
            direction = wantedDirection;
        } else {
            if (!move(direction, dt, maze)){
                position = new Position(Math.round(position.x()), Math.round(position.y()));
            }
        }
    }

    public List<Position> getTouchingTiles(Position p) {
        final double offset = 1 - 1e-5;
        List<Position> list = Arrays.asList(
            new Position(p.x(), p.y()),
            new Position(p.x() + offset, p.y()),
            new Position(p.x(), p.y() + offset),
            new Position(p.x() + offset, p.y() + offset));
        return list;
    }



    private boolean move(Direction direction, double dt, Maze maze) {
        double newX = position.x() + speed * dt * direction.dx;
        double newY = position.y() + speed * dt * direction.dy;


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

        Position newPos = new Position(newX, newY);

        if (!getTouchingTiles(newPos).stream()
                .anyMatch(e -> disallowedTiles.contains(maze.getTile(e)))) {
            position = newPos;
            return true;
        }
        return false;
    }

    public Position getPostition() {
        return position;
    }
    public Direction getDirection() {
        return direction;
    }

    public Direction getWantedDirection() {
        return wantedDirection;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        wantedDirection = direction;
    }

    public void setWantedDirection(Direction direction) {
        if (direction != Direction.NONE)
            wantedDirection = direction;
    }

    public void setX(double x) { position = new Position(x, position.y()); }
    public void setY(double y) { position = new Position(position.x(), y); }

    // for new level
    public void setSpeed(int addSpeed){
        speed = speed + addSpeed;
    }
}
