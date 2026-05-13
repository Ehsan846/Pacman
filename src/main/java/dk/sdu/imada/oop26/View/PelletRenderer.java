package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import dk.sdu.imada.oop26.Model.GameModel;
import dk.sdu.imada.oop26.Model.Position;
import dk.sdu.imada.oop26.Model.Maze;
import dk.sdu.imada.oop26.Model.Subject;
import dk.sdu.imada.oop26.Model.Tile;

public class PelletRenderer implements Renderable {
    private GameModel model;

    public PelletRenderer() { }

    // Renderable
    @Override
    public void render(GraphicsContext gc) {
        if (model == null)
            return;

        final Maze maze = model.getMaze();
        gc.setFill(Color.ORANGE);
        for (int x = 0; x < Maze.MAZE_WIDTH; x++) {
            for (int y = 0; y < Maze.MAZE_HEIGHT; y++) {
                Position p = new Position(x, y);
                if (maze.getTile(p) == Tile.Pellet)
                    gc.fillOval(
                        x * GameView.PPT - 3, y * GameView.PPT - 3, 6, 6
                    );
                else if (maze.getTile(p) == Tile.PowerPellet)
                    gc.fillOval(
                        x * GameView.PPT - 7, y * GameView.PPT - 7, 14, 14
                    );
            }
        }
    }

    @Override
    public int getLayer() {
        return 1;
    }

    // Observer
    @Override
    public void update(final Subject subject) {
        if (subject instanceof GameModel gameModel)
            model = gameModel;
    }
}
