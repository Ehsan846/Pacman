package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.GameState;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.Subject;

public class UIRenderer implements Renderable {
    private int lives;
    private int score;
    private int level;
    private GameState state;
    private int startingIn;

    public static final Position LOWER_UI = new Position(75, GameView.HEIGHT - 20 - GameView.PPT / 2);

    public UIRenderer() { }

    // Renderable
    @Override
    public void render(GraphicsContext gc) {
        renderLives(gc);

        gc.setFill(Color.WHITE);
        renderLevel(gc);
        renderScore(gc);
        
        gc.setFont(new Font(40));
        gc.setFill(Color.RED);
        gc.setTextAlign(TextAlignment.CENTER);
        if (state == GameState.STARTING)
            renderStarting(gc);
        else if (state == GameState.FINISHED)
            renderFinished(gc);
    }

    @Override
    public int getLayer() {
        return 4;
    }

    // Observer
    @Override
    public void update(final Subject subject) {
        if (subject instanceof GameModel model) {
            lives = model.getLives();
            score = model.getScore();
            level = model.getLevel();
            state = model.getState();
            startingIn = (int)Math.ceil(model.getStartDelay());
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
        gc.fillText(Integer.toString(score), GameView.WIDTH - 200, GameView.HEIGHT - 20);
    }

    private void renderLevel(GraphicsContext gc) {
        gc.fillText("Level " + Integer.toString(level), 200, GameView.HEIGHT - 20);
    }

    private void renderStarting(GraphicsContext gc) {
        gc.fillText(Integer.toString(startingIn) + "  ", GameView.WIDTH / 2, GameView.HEIGHT / 2);
    }
    
    private void renderFinished(GraphicsContext gc) {
        if (score != 0) {
            gc.fillText("Game Over  ", GameView.WIDTH / 2, GameView.HEIGHT / 3);
            gc.fillText("Score: " + Integer.toString(score) + "  ", GameView.WIDTH / 2, GameView.HEIGHT / 3 + 40);
        }
        gc.fillText("Press any key to start  ", GameView.WIDTH / 2, GameView.HEIGHT / 3 + 150);
    }
}
