package dk.sdu.imada.oop26.Model;

public record Position(double x, double y) {
    public static Position floorPosition(Position p) {
        return new Position(Math.floor(p.x()), Math.floor(p.y()));
    }
}
