package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.Subject;

public class UIRenderer implements Renderable {
    private int lives;
    private int score;
    private int level;

    public static final Position LOWER_UI = new Position(75, GameView.HEIGHT - 20 - GameView.PPT / 2);

    public UIRenderer() { }

    // Renderable
    @Override
    public void render(GraphicsContext gc) {
        renderLives(gc);
        
        renderLevel(gc);
        renderScore(gc);
    }

    @Override
    public int getLayer() {
        return 0;
    }

    // Observer
    @Override
    public void update(final Subject subject) {
        if (subject instanceof GameModel model) {
            lives = model.getLives();
            score = model.getScore();
            level = model.getLevel();
        }
    }

    private void renderLives(GraphicsContext gc) {
        for (int i = 0; i < lives - 1; i++) {
            Sprites.lives.draw(
                gc, LOWER_UI.x() + i * Sprites.DRAWSIZE, LOWER_UI.y()
            );
        }
    }
    
    private void renderScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(score), GameView.WIDTH - 200, GameView.HEIGHT - 20);
    }

    private void renderLevel(GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.fillText("Level " + Integer.toString(level), 200, GameView.HEIGHT - 20);
    }
}
