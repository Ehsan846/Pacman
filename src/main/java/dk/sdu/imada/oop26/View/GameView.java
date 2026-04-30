package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.text.Font;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.GhostType;
import dk.sdu.imada.oop26.Model.Ghost;
import dk.sdu.imada.oop26.Model.Maze;
import dk.sdu.imada.oop26.Model.Observer;
import dk.sdu.imada.oop26.Model.Subject;
import dk.sdu.imada.oop26.Model.Tile;
import dk.sdu.imada.oop26.Model.GameState;
import dk.sdu.imada.oop26.Controller.GameController;


public class GameView implements Observer {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private Sprite backgroundImage;

    // pixel per tile
    public static final int PPT = 23;

    public static final int WIDTH = 644;
    public static final int HEIGHT = 754;

    public static final double TIME_PER_FRAME = 0.015;



    private int frame = 0;
    private int firstDeathFrame = -1;
    private double total_time;


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

    public Scene getScene() {
        return scene;
    }

    // Observer
    @Override
    public void update(final Subject subject, double dt) {
        if (subject instanceof GameModel)
            drawGame((GameModel)subject, dt);
    }

    public void drawGame(GameModel model, double dt) {
        total_time += dt;
        if (total_time > TIME_PER_FRAME) {
            total_time = 0;
            frame++;
        }
        drawBackground(model);

        gc.save();
        gc.translate(PPT / 2, PPT / 2); // Offset for centering

        drawMaze(model);
        drawPacman(model);
        drawGhosts(model);
        drawScore(model);
        drawLevel(model);

        gc.restore();
    }

    private void drawBackground(GameModel model) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        backgroundImage.draw(gc, 0, 0);

        for (int i = 0; i < model.gethp() - 1; i++) {
            Sprites.hp.draw(gc, 75 + i * Sprites.DRAWSIZE, HEIGHT - 20);
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
        if (model.pacmanAlive()) {
            if (model.pacmanImmune() && (frame % 10 < 5))
                return;

            final int rotationOffset = 4 * Sprites.getRotationOffset(model.getDirection());
            
            Sprites.pacman[(frame / 3) % 4 + rotationOffset].draw(
               gc, model.getx() * PPT, model.gety() * PPT
            );

            firstDeathFrame = -1;
        } else {
            if (firstDeathFrame < 0) {
                firstDeathFrame = frame;
            }

            int index = ((frame - firstDeathFrame - 50) / 10);
            if (index > 10)
                return;
            if (index < 0) {
                Sprites.pacman[2].draw(
                    gc, model.getx() * PPT, model.gety() * PPT
                );
            } else {
                Sprites.pacmanDeath[index].draw(
                    gc, model.getx() * PPT, model.gety() * PPT
                );
            }
        }
    }

    private void drawGhosts(GameModel model) {
        if (firstDeathFrame > 0 && frame - firstDeathFrame > 50)
            return;
        
        for (Ghost ghost : model.getGhosts()) {
            final int rotationOffset = Sprites.getRotationOffset(ghost.getDirection());
            final int spriteOffset = Sprites.getGhostOffset(ghost.gettype());

            Sprite ghostSprite = Sprites.ghostDeath[rotationOffset];
            if (ghost.isAlive()) {
                if (model.getState() == GameState.POWER) {
                    final double duration = model.getPowerDuration();
                    int blinkOffset = 0;
                    if (duration < 4 && ((int)(duration * 3)) % 2 == 1) {
                        blinkOffset = 2;
                    }
                    ghostSprite = Sprites.ghostPower[(frame / 10) % 2 + blinkOffset];
                } else {
                    ghostSprite = Sprites.ghosts[spriteOffset + 2 * rotationOffset + (frame / 10) % 2];
                }
            }

            ghostSprite.draw(
                gc, ghost.getx() * PPT, ghost.gety() * PPT
            );
        }
    }

    private void drawScore(GameModel model) {
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(model.getScore()), WIDTH - 200, HEIGHT - 20);
    }
    
    private void drawLevel(GameModel model){
        gc.setFill(Color.WHITE);
        gc.fillText("Level " + Integer.toString(model.getLevel()), 200, HEIGHT - 20);
    }
}
