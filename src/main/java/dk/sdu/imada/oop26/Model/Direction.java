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
}
