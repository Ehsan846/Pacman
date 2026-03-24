package dk.sdu.imada.oop26;

public class PacmanModel {
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;

    PacmanModel() {
        x = 10;
        y = 20;
    }

    public void move(double dt) {
        x += xSpeed*dt;
        y += ySpeed*dt;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }


    public void setXSpeed(double x) {
        xSpeed = x;
    }

    public void setYSpeed(double y) {
        ySpeed = y;
    }
}
