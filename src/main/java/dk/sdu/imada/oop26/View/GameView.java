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


    public GameView(GameController gamecontroller) {
        canvas = new Canvas(1600, 1000);
        scene = new Scene(new Group(canvas));
        gc = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(event -> gamecontroller.keypressed(event));

        backgroundImage = new Sprite("board.png");
    }

    public Scene getScene() {
        return scene;
    }

    public void drawGame(GameModel model) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1600, 1000);
        backgroundImage.draw(gc, 0, 0);

        gc.setFill(Color.YELLOW);
        gc.fillOval(model.getx()*PPT-PPT/2, model.gety()*PPT-PPT/2, PPT*2, PPT*2);

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
            gc.fillOval(ghost.getx(), ghost.gety(), 40, 40);
        }

        Maze maze = model.getMaze();
        gc.setFill(Color.PURPLE);
        for (int x = 0; x < maze.MAZE_WIDTH; x++) {
            for (int y = 0; y < maze.MAZE_HEIGHT; y++) {
                if (maze.getTile(x, y) == Tile.Pellet)
                    gc.fillOval(PPT/2 + x*PPT-3, PPT/2 + y*PPT-3, 6, 6);
                if (maze.getTile(x, y) == Tile.PowerPellet)
                    gc.fillOval(PPT/2 + x*PPT-7, PPT/2 + y*PPT-7, 14, 14);
            }
        }

    }
}
