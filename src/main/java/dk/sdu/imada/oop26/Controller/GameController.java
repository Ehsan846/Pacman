package dk.sdu.imada.oop26.Controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.PacmanModel;
import dk.sdu.imada.oop26.View.GameView;
 
public class GameController {
    private GameView view;
    private PacmanModel model;

    public GameController() {
        model = new PacmanModel();
        view = new GameView(model, this);
    }

    public void start() {
        new AnimationTimer() {
            double lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime) {
                double dt = (currentNanoTime - lastNanoTime)/1000000000.0;
                lastNanoTime = currentNanoTime;

                model.update(dt);
                view.update();

            }
        }.start();
    }

    public void keypressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                model.setDirection(Direction.UP);
                break;
            case S:
            case DOWN:
                model.setDirection(Direction.DOWN);
                break;
            case A:
            case LEFT:
                model.setDirection(Direction.LEFT);
                break;
            case D:
            case RIGHT:
                model.setDirection(Direction.RIGHT);
                break;
        }
    }

    public Scene getScene() {
        return view.getScene();
    }
}
