package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;
import dk.sdu.imada.oop26.Model.Observer;

public interface Renderable extends Observer {
    public void render(GraphicsContext gc);

    public int getLayer();
}
