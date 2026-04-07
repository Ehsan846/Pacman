package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.GhostType;
import dk.sdu.imada.oop26.Model.Ghost;
import dk.sdu.imada.oop26.Model.Maze;
import dk.sdu.imada.oop26.Model.Tile;
import dk.sdu.imada.oop26.Controller.GameController;


public class GameView {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private Sprite backgroundImage;

    // pixel per tile
    public static final int PPT = 23;
    
    public static final int WIDTH = 644;
    public static final int HEIGHT = 754;



    private int frame = 0;


    public GameView(GameController gamecontroller) {
        canvas = new Canvas(WIDTH, HEIGHT);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));

        gc.setImageSmoothing(false);
        backgroundImage = new Sprite("board.png");

        Sprite spritesheet = new Sprite("spritesheet.png");
        Sprites.loadSprites(spritesheet.getImage());
    }

    public Scene getScene() {
        return scene;
    }

    public void drawGame(GameModel model) {
        frame++;
        drawBackground(model);

        gc.save();
        gc.translate(PPT / 2, PPT / 2); // Offset for centering

        drawMaze(model);
        drawPacman(model);
        drawGhosts(model);

        gc.restore();
    }

    private void drawBackground(GameModel model) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        backgroundImage.draw(gc, 0, 0);

        for (int i = 0; i < 5; i++) {
            Sprites.hpSprite.draw(gc, 75 + i * 40, HEIGHT - 20);
        }
    }

    private void drawMaze(GameModel model) {
        Maze maze = model.getMaze();
        gc.setFill(Color.PURPLE);
        for (int x = 0; x < Maze.MAZE_WIDTH; x++) {
            for (int y = 0; y < Maze.MAZE_HEIGHT; y++) {
                if (maze.getTile(x, y) == Tile.Pellet)
                    gc.fillOval(x * PPT - 3, y * PPT - 3, 6, 6);
                    // gc.fillOval(PPT/2 + x*PPT-3, PPT/2 + y*PPT-3, 6, 6);
                if (maze.getTile(x, y) == Tile.PowerPellet)
                    gc.fillOval(x * PPT - 7, y * PPT - 7, 14, 14);
                    // gc.fillOval(PPT/2 + x*PPT-7, PPT/2 + y*PPT-7, 14, 14);
            }
        }
    }

    private void drawPacman(GameModel model) {
        final int rotationOffset = 4 * Sprites.getRotationOffset(model.getDirection());

        Sprites.pacmanSprites[(frame / 3) % 4 + rotationOffset].draw(
           gc, model.getx() * PPT, model.gety() * PPT
        );
    }

    private void drawGhosts(GameModel model) {
        for (Ghost ghost : model.getghosts()) {
            final int rotationOffset = 2 * Sprites.getRotationOffset(ghost.getDirection());
            final int spriteOffset = Sprites.getGhostOffset(ghost.gettype());
            Sprites.ghostSprites[spriteOffset + rotationOffset + (frame / 10) % 2].draw(gc, ghost.getx() * PPT, ghost.gety() * PPT);
        }
    }
}
