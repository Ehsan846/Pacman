package dk.sdu.imada.oop26.Controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;


import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.View.GameView;

public class GameController {
    private GameView view;
    private GameModel model;

    public GameController() {
        model = new GameModel();
        view = new GameView(this);
    }

    public void start() {
        new AnimationTimer() {
            double lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime) {
                double dt = (currentNanoTime - lastNanoTime)/1000000000.0;
                lastNanoTime = currentNanoTime;

                model.update(dt);
                view.drawGame(model, dt);

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
