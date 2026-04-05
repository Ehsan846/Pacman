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
    public static final int HEIGHT = 714;


    public GameView(GameController gamecontroller) {
        canvas = new Canvas(WIDTH, HEIGHT);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));

        backgroundImage = new Sprite("board.png");
    }

    public Scene getScene() {
        return scene;
    }

    public void drawGame(GameModel model) {
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
        gc.setFill(Color.YELLOW);
        gc.fillOval(model.getx() * PPT - 20, model.gety() * PPT - 20, 40, 40);
    }

    private void drawGhosts(GameModel model) {
        for (Ghost ghost : model.getghosts()) {
            switch (ghost.gettype()) {
                case INKY:
                    gc.setFill(Color.BLUE);
                    break;
                case PINKY:
                    gc.setFill(Color.PINK);
                    break;
                case BLINKY:
                    gc.setFill(Color.RED);
                    break;
                case CLYDE:
                    gc.setFill(Color.ORANGE);
                    break;
            }
            gc.fillOval(ghost.getx() * PPT - 20, ghost.gety() * PPT - 20, 40, 40);
        }
    }
}
