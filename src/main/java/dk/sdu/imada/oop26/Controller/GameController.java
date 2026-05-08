package dk.sdu.imada.oop26.Controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.imada.oop26.Model.Blinky;
import dk.sdu.imada.oop26.Model.Clyde;
import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.Ghost;
import dk.sdu.imada.oop26.Model.GhostType;
import dk.sdu.imada.oop26.Model.Inky;
import dk.sdu.imada.oop26.Model.Pinky;
import dk.sdu.imada.oop26.Model.Observer;
import dk.sdu.imada.oop26.View.GameView;
import dk.sdu.imada.oop26.View.Renderable;
import dk.sdu.imada.oop26.View.PacmanRenderer;
import dk.sdu.imada.oop26.View.GhostRenderer;
import dk.sdu.imada.oop26.View.PelletRenderer;
import dk.sdu.imada.oop26.View.UIRenderer;

public class GameController extends Application {
    private GameView view;
    private GameModel model;

    public GameController() {
        model = new GameModel();

        List<Renderable> renderables = createRenderables();
        for (Renderable renderable : renderables)
            model.registerObserver(renderable);
        
        view = new GameView(this, renderables);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Pac-Man");
        new AnimationTimer() {
            double lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime) {
                double dt = (currentNanoTime - lastNanoTime)/1000000000.0;
                lastNanoTime = currentNanoTime;

                model.update(dt);
                view.update(dt);
            }
        }.start();
        primaryStage.show();
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

    private List<Renderable> createRenderables() {
        List<Renderable> renderables = new ArrayList<Renderable>(7);
        renderables.add(new PelletRenderer());
        renderables.add(new UIRenderer());
        renderables.add(new PacmanRenderer());

        for (GhostType ghostType : GhostType.values())
            renderables.add(new GhostRenderer(ghostType));
        
        return renderables;
    }
}
