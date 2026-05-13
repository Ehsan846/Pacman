package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.Ghost;
import dk.sdu.imada.oop26.Model.GhostState;
import dk.sdu.imada.oop26.Model.GhostType;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.Subject;

public class GhostRenderer extends Animator implements Renderable {
    private final GhostType ghostType;

    private Direction direction;
    private Position position = new Position(0, 0);

    private GhostState prevState = GhostState.DEAD;
    private double prevDuration = 0;

    public GhostRenderer(GhostType ghostType) {
        this.ghostType = ghostType;
        setSprites(Sprites.getGhostSprites(ghostType), true, 0.05);
    }

    // Renderable
    @Override
    public void render(GraphicsContext gc) {
        getDirectionalSprite(direction).draw(
            gc, position.x() * GameView.PPT, position.y() * GameView.PPT
        );
    }

    @Override
    public int getLayer() {
        return 2;
    }

    // Observer
    @Override
    public void update(final Subject subject) {
        if (subject instanceof GameModel model) {
            final Ghost ghost = getGhostFromModel(model);
            
            position = ghost.getPostition();
            direction = ghost.getDirection();
            
            updateAnimationState(model, ghost);
        }
    }

    public GhostType getType() {
        return ghostType;
    }

    private Ghost getGhostFromModel(GameModel model) {
        for (Ghost ghost : model.getGhosts()) {
            if (ghost.gettype() == ghostType) {
                return ghost;
            }
        }
        return null;
    }

    private void updateAnimationState(GameModel model, Ghost ghost) {
        final GhostState newState = ghost.getState();
        if (prevState == newState) {
            if (newState == GhostState.SCARED) {
                updateBlinking(model);
            }
            return;
        }

        // New state
        if (newState == GhostState.SCARED) {
            setSprites(Sprites.ghostScared, false, 0.05);
        } else if (newState == GhostState.DEAD) {
            setSprites(Sprites.ghostDeath, true, 0.05);
        } else {
            setSprites(Sprites.getGhostSprites(ghostType), true, 0.05);
        }

        prevState = newState;
    }

    private void updateBlinking(GameModel model) {
        final double duration = model.getPowerDuration();
        
        if (duration <= 2 && prevDuration > 2) {
            setSprites(Sprites.ghostBlinking, false, 0.05);
        }
        prevDuration = duration;
    }
}
