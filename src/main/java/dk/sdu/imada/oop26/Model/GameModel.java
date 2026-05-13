package dk.sdu.imada.oop26.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameModel implements Subject {
    private GameState state = GameState.FINISHED;
    private double stateDuration = 0;
    private double startDelay = 0;
    private int lives = STARTING_LIVES;
    private int level = 1;

    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;
    private int score;

    // Observers
	private final List<Observer> observers = new LinkedList<>();

    public final static int STARTING_LIVES = 3;

    public GameModel() {
        resetGame();
        state = GameState.FINISHED;
    }

    public void update(double dt) {
        if (!handleState(dt)) {
            return; // Enters when game is not running
        }

        pacman.update(dt, maze);

        for (Position e : pacman.getTouchingTiles(pacman.getPostition())) {
            Tile tile = maze.getTile(e);
            // pacman ignore pellet
            if (tile == Tile.Pellet) {
                maze.emptyTile(e);
                score++;
            } else if (tile == Tile.PowerPellet) {
                score++;
                maze.emptyTile(e);

                state = GameState.POWER;
                stateDuration = 4;
                for (Ghost ghost : ghosts)
                    ghost.scared();
            }
        }


        for (Ghost ghost : ghosts) {
            ghost.update_direction(pacman, maze);
            ghost.update(dt, maze);
            if (ghost.isAlive()) {
                checkGhostCollission(ghost);
            } else if (maze.getTile(ghost.getPostition()) == Tile.GhostRespawn) {
                if (state != GameState.POWER && state != GameState.IMMUNE) {
                    ghost.normal();
                } else {
                    ghost.scared();
                }
            }
        }

        notifyObservers();
    }

    private void resetGame() {
        state = GameState.STARTING;
        stateDuration = 0;
        startDelay = 3;

        // Just redefine our stuff, since the old stuff no longer have a reference they are picked up by garbage collector
        maze = new Maze();

        pacman = new Pacman();

        ghosts = new ArrayList<Ghost>(4);
        ghosts.add(new Pinky());
        ghosts.add(new Clyde());
        Blinky blinky = new Blinky();
        ghosts.add(blinky);
        ghosts.add(new Inky(blinky));
        notifyObservers();
    }

    private void restartGame() {
        lives = STARTING_LIVES;
        level = 1;
        score = 0;
        resetGame();
    }

    private void pacmanDeath() {
        if (--lives < 1) {
            state = GameState.FINISHED;
            return;
        }

        state = GameState.IMMUNE;
        stateDuration = 2;
    }

    private void checkGhostCollission(Ghost ghost) {
        final double pacmanX = pacman.getPostition().x();
        final double pacmanY = pacman.getPostition().y();
        final double ghostX = ghost.getPostition().x();
        final double ghostY = ghost.getPostition().y();

        if (pacmanX < ghostX + 1.5 && pacmanX > ghostX - 1.5
            && pacmanY < ghostY + 1.5 && pacmanY > ghostY - 1.5) {
            if (state == GameState.POWER) {
                ghost.eat();
                score++;
            } else if (state != GameState.IMMUNE ) {
                pacmanDeath();
            }
        }
    }

    // Returns false when dead/finished
    private boolean handleState(double dt) {
        if (state == GameState.FINISHED){
            return false;
        } else if (state == GameState.STARTING) {
            startDelay -= dt;
            if (startDelay <= 0) {
                startDelay = 0;
                state = GameState.NORMAL;
            }
            notifyObservers();
            return false;
        }

        //all pellets eaten games done
        if (maze.allPelletsEaten()){
            resetGame();
            level ++;
            return false;
        }

        if (state == GameState.NORMAL)
            return true;

        stateDuration -= dt;
        if (stateDuration <= 0) {
            state = GameState.NORMAL;
            for (Ghost ghost : ghosts)
                if (ghost.getState() != GhostState.DEAD){
                ghost.normal();
            }

        }

        return true;
    }


    public GameState getState() {
        return state;
    }

    public double getPowerDuration() {
        return stateDuration;
    }

    public double getStartDelay() {
        return startDelay;
    }

    public boolean pacmanAlive() {
        return state != GameState.FINISHED;
    }

    public boolean pacmanImmune() {
        return state == GameState.IMMUNE;
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Position getPostition() {
        return pacman.getPostition();
    }

    public int getScore() {
        return score;
    }

    public int getLevel(){
        return level;
    }

    public int getLives() {
        return lives;
    }

    public Direction getDirection() {
        return pacman.getDirection();
    }

    public void setDirection(Direction direction) {
        if (state == GameState.FINISHED)
            restartGame();

        pacman.setWantedDirection(direction);
    }

    // Subject interface
    @Override
    public boolean registerObserver(final Observer observer) {
        return observers.add(observer);
    }

    @Override
	public boolean deregisterObserver(final Observer observer) {
        return observers.remove(observer);
    }

    @Override
	public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }
}

