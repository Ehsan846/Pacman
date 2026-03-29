package dk.sdu.imada.oop26.View;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
    private Image sprite;
    public Sprite(String filePath) {
        sprite = new Image(getClass().getResourceAsStream("/" + filePath));
    }

    public void draw(GraphicsContext gc, double x, double y) {
        gc.drawImage(sprite, x, y);
    }
}
