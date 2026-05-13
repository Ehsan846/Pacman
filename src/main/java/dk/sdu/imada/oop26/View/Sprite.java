package dk.sdu.imada.oop26.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;

public class Sprite {
    private Image sprite;
    private int targetWidth = Sprites.DRAWSIZE, targetHeight = Sprites.DRAWSIZE;

    public Sprite(String filePath) {
        sprite = new Image(getClass().getResourceAsStream("/" + filePath));
        targetWidth = 0;
        targetHeight = 0;
    }

    public Sprite(Image spritesheet, int x, int y, int width, int height, int targetWidth, int targetHeight) {
        PixelReader reader = spritesheet.getPixelReader();
        sprite = new WritableImage(reader, x, y, width, height);
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }
    
    public Sprite(Image spritesheet, int x, int y) {
        PixelReader reader = spritesheet.getPixelReader();
        sprite = new WritableImage(reader, x, y, Sprites.SIZE, Sprites.SIZE);
    }

    public Sprite(Image spritesheet, int x, int y, int width, int height) {
        this(spritesheet, x, y, width, height, 0, 0);
    }

    public void draw(GraphicsContext gc, double x, double y) {
        if (targetWidth != 0 || targetHeight != 0) {
            gc.drawImage(sprite, x - targetWidth / 2, y - targetHeight / 2 + 1, targetWidth, targetHeight);
        } else {
            gc.drawImage(sprite, x, y);
        }
    }

    public Image getImage() {
        return sprite;
    }
}
