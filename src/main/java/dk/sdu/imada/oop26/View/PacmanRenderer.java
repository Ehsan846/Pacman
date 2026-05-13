package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.GameState;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.Subject;

public class PacmanRenderer extends Animator implements Renderable {
    private Direction direction;
    private Position position = new Position(0, 0);
    private double stateDuration;

    GameState prevState = GameState.FINISHED;

    public PacmanRenderer() {
        setSprites(Sprites.pacman, true, 0.05);
    }

    // Renderable
    @Override
    public void render(GraphicsContext gc) {
        if (prevState == GameState.IMMUNE
            && (int)(stateDuration * 15) % 2 == 0
        ) return;

        getDirectionalSprite(direction).draw(
            gc, position.x() * GameView.PPT, position.y() * GameView.PPT
        );
    }

    @Override
    public int getLayer() {
        return 3;
    }

    // Observer
    @Override
    public void update(final Subject subject) {
        if (subject instanceof GameModel model) {
            position = model.getPostition();
            direction = model.getDirection();
            stateDuration = model.getPowerDuration();

            updateAnimationState(model);
        }
    }

    private void updateAnimationState(GameModel model) {
        final GameState newState = model.getState();
        if (newState == prevState)
            return;

        if (newState == GameState.FINISHED) {
            // Change to death animation
            setSprites(Sprites.pacmanDeath, false, 0.25);
            setLooping(false);
        } else {
            // Change to standard pacman
            setSprites(Sprites.pacman, true, 0.05);
            setLooping(true);
        }
        prevState = newState;
    }
}
