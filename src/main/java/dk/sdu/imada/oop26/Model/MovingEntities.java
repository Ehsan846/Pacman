package dk.sdu.imada.oop26.Model;

public abstract class MovingEntities {
    protected double x, y, speed;
    Direction direction = Direction.NONE;

    public void update(double dt) {
        // Collision check her?
        // Reference til maze?
        
        x += speed * dt * direction.dx;
        y += speed * dt * direction.dy;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }
    //public void setX(double x) this.x = x; 
    //public void setY(double y) this.y = y;
}
