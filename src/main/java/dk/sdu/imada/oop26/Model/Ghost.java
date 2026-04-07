package dk.sdu.imada.oop26.Model;

public class Ghost extends MovingEntity {
    protected GhostType type;

    public Ghost() {
        speed = 8;
    }

    public GhostType gettype() {
        return type;
    }


}
