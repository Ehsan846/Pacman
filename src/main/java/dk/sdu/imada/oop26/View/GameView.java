package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import dk.sdu.imada.oop26.Model.PacmanModel;
import dk.sdu.imada.oop26.Controller.GameController;


public class GameView {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private PacmanModel pacman;

    private GameController gamecontroller;

    public GameView(PacmanModel pacman, GameController gamecontroller) {
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


        // TEST :D
        Sprite sprite = new Sprite("board.png");
        sprite.draw(gc, pacman.getx(), pacman.gety());
    }
}
