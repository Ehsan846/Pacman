package dk.sdu.imada.oop26.Model;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class AStar {
    private static HashMap<Entry<Integer, Integer>, Entry<Integer, Integer>> cameFrom = new HashMap<>();
    private static HashMap<Entry<Integer, Integer>, Integer> gScore = new HashMap<>();
    private static HashMap<Entry<Integer, Integer>, Integer> fScore = new HashMap<>();
    private static PriorityQueue<Entry<Integer, Integer>> openSet = new PriorityQueue<>((a, b) ->
                                  Integer.compare(fScore.getOrDefault(a, Integer.MAX_VALUE),
                                                  fScore.getOrDefault(b, Integer.MAX_VALUE)));

    private static Entry<Integer, Integer> encode_coord(int x, int y) {
        return new SimpleEntry(x, y);
    }
    private static int getx(Entry<Integer, Integer> c) {
        return c.getKey();
    }

    private static int gety(Entry<Integer, Integer> c) {
        return c.getValue();
    }

    private static int distHeuristic(Entry<Integer, Integer> a, Entry<Integer, Integer> b) {
        int ax = getx(a);
        int ay = gety(a);
        int bx = getx(b);
        int by = gety(b);
        return Math.abs(ax-bx) + Math.abs(ay-by);

    }

    private static Direction getLastDir(Entry<Integer, Integer> start, Entry<Integer, Integer> end) {
        Entry<Integer, Integer> second_last = end;
        Entry<Integer, Integer> next = cameFrom.get(second_last);
        while (!next.equals(start)){
            second_last = next;
            next = cameFrom.get(second_last);
        }
        int dx = getx(second_last) - getx(start);
        int dy = gety(second_last) - gety(start);
        for (Direction d : Direction.getDirections()) {
            if (d.dx == dx && d.dy == dy) {
                return d;
            }
        }
        return Direction.NONE;
    }

    protected static Direction astar(Maze maze, int startX, int startY, int targetX, int targetY, Direction facingDirection) {
        if (maze.getTile(startX, startY) == Tile.Wall)
            return Direction.NONE;
        if (maze.getTile(targetX, targetY) == Tile.Wall)
            return Direction.NONE;

        Entry<Integer, Integer> start = encode_coord(startX, startY);
        Entry<Integer, Integer> target = encode_coord(targetX, targetY);

        if (start.equals(target))
            return Direction.NONE;

        cameFrom.clear();
        gScore.clear();
        fScore.clear();
        openSet.clear();

        gScore.put(start, 0);
        fScore.put(start, 0);
        openSet.offer(start);

        final Direction oppositeDirection = Direction.oppositeDirection(facingDirection);

        while (openSet.size() > 0) {
            Entry<Integer, Integer> current = openSet.poll();
            if (current.equals(target)) {
                return getLastDir(start, current);
            }

            for (Direction d : Direction.getDirections()) {
                if (current.equals(start) && d == oppositeDirection)
                    continue;

                int neighborx = getx(current) + d.dx;
                int neighbory = gety(current) + d.dy;
                if (neighborx < 0 || neighborx >= Maze.MAZE_WIDTH || neighbory < 0 || neighbory >= Maze.MAZE_HEIGHT)
                    continue;
                if (maze.getTile(neighborx, neighbory) == Tile.Wall)
                    continue;
                Entry<Integer, Integer> neighbor = encode_coord(neighborx, neighbory);
                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + distHeuristic(neighbor, target));
                    if (!openSet.contains(neighbor)) {
                        openSet.offer(neighbor);
                    }
                }
            }
        }
        return Direction.NONE;
    }
}

