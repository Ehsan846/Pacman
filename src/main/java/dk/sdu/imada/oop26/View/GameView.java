package dk.sdu.imada.oop26;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class GameView {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private PacmanModel pacman;

    private GameController gamecontroller;

    GameView(PacmanModel pacman, GameController gamecontroller) {
        canvas = new Canvas(1600, 1000);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        this.pacman = pacman;
        this.gamecontroller = gamecontroller;
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));
    }

    public Scene getScene() {
        return scene;
    }

    public void update() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1600, 1000);

        gc.setFill(Color.YELLOW);
        gc.fillOval(pacman.getx(), pacman.gety(), 40, 40);
    }
}
