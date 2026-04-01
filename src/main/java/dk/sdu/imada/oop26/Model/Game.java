package dk.sdu.imada.oop26.Model;

public class Game {
    private int score = 0;
    private int time;
    private State state;

    private Maze maze;
    private Pacman pacman;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    private Clyde clyde;

    public Game() {
        this.maze   = new Maze();
        this.pacman = new Pacman();
        this.blinky = new Blinky();
        this.inky   = new Inky();
        this.pinky  = new Pinky();
        this.clyde  = new Clyde();
        this.state  = State.BEGIN;
    }

    public void addScore(int numb){
        score += numb;
    }

    public enum State {
        BEGIN, NORMAL, POWER, END
    }
}