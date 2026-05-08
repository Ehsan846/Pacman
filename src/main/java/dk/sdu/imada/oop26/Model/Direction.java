package dk.sdu.imada.oop26.Model;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NONE(0, 0);

    public final int dx;
    public final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    private static final Direction[] directions = new Direction[]{UP, DOWN, LEFT, RIGHT};

    public static Direction[] getDirections() {
        return directions;
    }

    public static Direction oppositeDirection(Direction d) {
        switch (d) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case NONE:
                return NONE;
            default:
                return NONE;
        }
    }
}
