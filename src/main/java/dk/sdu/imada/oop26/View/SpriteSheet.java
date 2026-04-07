// package dk.sdu.imada.oop26.View;

// import javafx.scene.image.Image;
// import javafx.scene.image.PixelReader;
// import javafx.scene.image.WritableImage;

// import javax.swing.ImageIcon;

// import javafx.scene.canvas.GraphicsContext;

// public class SpriteSheet {
//     private Image spritesheet;

//     public Sprite(String filePath) {
//         sprite = new Image(getClass().getResourceAsStream("/" + filePath));
//     }
    
//     public Sprite(Sprite spritesheet, int x, int y, int width, int height) {
//         PixelReader reader = spritesheet.getImage().getPixelReader();
//         sprite = new WritableImage(reader, x, y, width, height);
//     }
    
//     public void draw(GraphicsContext gc, double x, double y) {
//         gc.drawImage(sprite, x, y);
//     }

//     public Image getImage() {
//         return sprite;
//     }
// }
