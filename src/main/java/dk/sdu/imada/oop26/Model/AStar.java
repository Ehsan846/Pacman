package dk.sdu.imada.oop26.Model;

import java.util.PriorityQueue;
import java.util.HashMap;

public class AStar {
    private static HashMap<Position, Position> cameFrom = new HashMap<>();
    private static HashMap<Position, Double> gScore = new HashMap<>();
    private static HashMap<Position, Double> fScore = new HashMap<>();
    private static PriorityQueue<Position> openSet = new PriorityQueue<>((a, b) ->
                                  Double.compare(fScore.getOrDefault(a, Double.MAX_VALUE),
                                                  fScore.getOrDefault(b, Double.MAX_VALUE)));

    private static double distHeuristic(Position a, Position b) {
        double ax = a.x();
        double ay = a.y();
        double bx = b.x();
        double by = b.y();
        return Math.abs(ax-bx) + Math.abs(ay-by);

    }

    private static Direction getLastDir(Position start, Position end) {
        Position second_last = end;
        Position next = cameFrom.get(second_last);
        while (!next.equals(start)){
            second_last = next;
            next = cameFrom.get(second_last);
        }
        double dx = second_last.x() - start.x();
        double dy = second_last.y() - start.y();
        for (Direction d : Direction.getDirections()) {
            if (d.dx == dx && d.dy == dy) {
                return d;
            }
        }
        return Direction.NONE;
    }

    protected static Direction astar(Maze maze, Position start, Position target, Direction facingDirection) {
        if (maze.getTile(start) == Tile.Wall)
            return Direction.NONE;
        if (maze.getTile(target) == Tile.Wall)
            return Direction.NONE;

        if (start.equals(target))
            return Direction.NONE;

        cameFrom.clear();
        gScore.clear();
        fScore.clear();
        openSet.clear();

        gScore.put(start, 0.0);
        fScore.put(start, 0.0);
        openSet.offer(start);

        final Direction oppositeDirection = Direction.oppositeDirection(facingDirection);

        while (openSet.size() > 0) {
            Position current = openSet.poll();
            if (current.equals(target)) {
                return getLastDir(start, current);
            }

            for (Direction d : Direction.getDirections()) {
                if (current.equals(start) && d == oppositeDirection)
                    continue;

                double neighbourx = current.x() + d.dx;
                double neighboury = current.y() + d.dy;
                Position neighbour = new Position(neighbourx, neighboury);
                if (neighbourx < 0 || neighbourx >= Maze.MAZE_WIDTH || neighboury < 0 || neighboury >= Maze.MAZE_HEIGHT)
                    continue;
                if (maze.getTile(neighbour) == Tile.Wall)
                    continue;
                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + 1;
                if (tentativeGScore < gScore.getOrDefault(neighbour, Double.MAX_VALUE)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeGScore);
                    fScore.put(neighbour, tentativeGScore + distHeuristic(neighbour, target));
                    if (!openSet.contains(neighbour)) {
                        openSet.offer(neighbour);
                    }
                }
            }
        }
        return Direction.NONE;
    }
}

