package dk.sdu.imada.oop26;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

public class GameController {
    private GameView view;
    private PacmanModel pacman;

    GameController() {
        pacman = new PacmanModel();
        view = new GameView(pacman, this);
    }

    public void start() {
        new AnimationTimer() {
            double lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime) {
                double dt = (currentNanoTime - lastNanoTime)/1000000000.0;
                lastNanoTime = currentNanoTime;

                pacman.move(dt);
                view.update();

            }
        }.start();
    }

    public void keypressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                pacman.setYSpeed(-40);
                break;
            case DOWN:
                pacman.setYSpeed(40);
                break;
            case LEFT:
                pacman.setXSpeed(-40);
                break;
            case RIGHT:
                pacman.setXSpeed(40);
                break;
        }

    }

    Scene getScene() {
        return view.getScene();
    }
}
