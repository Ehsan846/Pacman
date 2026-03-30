package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Controller.GameController;


public class GameView {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private GameController gamecontroller;

    public GameView(GameController gamecontroller) {
        canvas = new Canvas(1600, 1000);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        this.gamecontroller = gamecontroller;
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));
    }

    public Scene getScene() {
        return scene;
    }

    public void update(GameModel model) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1600, 1000);

        gc.setFill(Color.YELLOW);
        gc.fillOval(model.getx(), model.gety(), 40, 40);


        // TEST :D
//        Sprite sprite = new Sprite("spritesheet.png");
//        sprite.draw(gc, model.getx(), model.gety());
    }
}
