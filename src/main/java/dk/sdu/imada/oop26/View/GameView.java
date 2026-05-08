package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.text.Font;
import dk.sdu.imada.oop26.Model.Blinky;
import dk.sdu.imada.oop26.Model.Clyde;
import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.GhostType;
import dk.sdu.imada.oop26.Model.Inky;
import dk.sdu.imada.oop26.Model.Ghost;
import dk.sdu.imada.oop26.Model.Maze;
import dk.sdu.imada.oop26.Model.Pinky;
import dk.sdu.imada.oop26.Model.Subject;
import dk.sdu.imada.oop26.Model.Tile;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.GameState;

import java.util.ArrayList;
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

    public GameView(GameController gamecontroller, List<Renderable> renderables) {
        this.renderables = renderables;
        sortRenderables();

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
        Collections.sort(renderables, new Comparator<Renderable>() {
            @Override
            public int compare(Renderable r1, Renderable r2) {
                if (r1.getLayer() > r2.getLayer())
                    return 1;
                else if (r1.getLayer() < r2.getLayer())
                    return -1;
                return 0;
            }
        });
    }
}
