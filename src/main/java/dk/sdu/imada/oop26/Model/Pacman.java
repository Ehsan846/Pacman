package dk.sdu.imada.oop26.Model;

public class Pacman extends MovingEntity {
    public Pacman() {
        position = new Position(13.5, 23);
        speed = 10;
        disallowedTiles.add(Tile.Gate);
    }

}
