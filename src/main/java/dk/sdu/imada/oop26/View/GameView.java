package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.text.Font;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import dk.sdu.imada.oop26.Controller.GameController;

public class GameView {
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private Sprite backgroundImage;

    private List<Renderable> renderables;

    // pixel per tile
    public static final int PPT = 23;
    public static final int WIDTH = 644;
    public static final int HEIGHT = 754;

    public GameView(GameController gamecontroller) {
        canvas = new Canvas(WIDTH, HEIGHT);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));

        gc.setImageSmoothing(false);
        gc.setFont(new Font(26));
        backgroundImage = new Sprite("board.png");

        Sprite spritesheet = new Sprite("spritesheet.png");
        Sprites.loadSprites(spritesheet.getImage());
    }

    public void setRenderables(List<Renderable> renderables) {
        this.renderables = renderables;
        sortRenderables();
    }

    public Scene getScene() {
        return scene;
    }

    public void update(double dt) {
        for (Renderable renderable : renderables) {
            if (renderable instanceof Animator animator)
                animator.update(dt);
        }

        drawGame();
    }

    private void drawGame() {
        drawBackground();

        gc.save();
        gc.translate(PPT / 2, PPT / 2); // Offset for centering

        for (Renderable renderable : renderables)
            renderable.render(gc);

        gc.restore();
    }

    private void drawBackground() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        backgroundImage.draw(gc, 0, 0);
    }

    private void sortRenderables() {
        Collections.sort(renderables, (a, b) -> Integer.compare(a.getLayer(), b.getLayer()));
    }
}
