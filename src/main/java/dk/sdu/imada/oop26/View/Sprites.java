package dk.sdu.imada.oop26.View;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GhostType;

public class Sprites {
    public static Sprite[] pacman;
    public static Sprite[] pacmanDeath;
    public static Sprite hp;
    public static final int pacmanSpriteCount = 4;

    public static Sprite[] ghosts;
    public static Sprite[] ghostPower;
    public static Sprite[] ghostDeath;

    public static final int DRAWSIZE = 40;

    private static final int OFFSET = 455;
    private static final int SIZE = 16;

    public static void loadSprites(Image sheet) {
        pacman = new Sprite[pacmanSpriteCount * 4];
        for (int i = 0; i < 4; i++) {
            pacman[i * pacmanSpriteCount] = new Sprite(sheet, OFFSET, i * SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
            pacman[i * pacmanSpriteCount + 1] = new Sprite(sheet, OFFSET + SIZE, i * SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
            pacman[i * pacmanSpriteCount + 2] = new Sprite(sheet, OFFSET + 2 * SIZE, 0, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
            pacman[i * pacmanSpriteCount + 3] = pacman[i * pacmanSpriteCount + 1];
        }

        pacmanDeath = new Sprite[11];
        for (int i = 0; i < 11; i++) {
            pacmanDeath[i] = new Sprite(sheet, OFFSET + (i + 3) * SIZE, 0, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
        }

        hp = new Sprite(sheet, OFFSET + 8 * SIZE + 1, SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);

        ghosts = new Sprite[32];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ghosts[i * 8 + j] = new Sprite(sheet, OFFSET + j * SIZE, (i + 4) * SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
            }
        }

        ghostPower = new Sprite[4];
        for (int i = 0; i < 4; i++) {
            ghostPower[i] = new Sprite(sheet, OFFSET + (i + 8) * SIZE, 4 * SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
        }

        ghostDeath = new Sprite[4];
        for (int i = 0; i < 4; i++) {
            ghostDeath[i] = new Sprite(sheet, OFFSET + (i + 8) * SIZE, 5 * SIZE, SIZE, SIZE, DRAWSIZE, DRAWSIZE);
        }
    }

    public static int getRotationOffset(Direction dir) {
        switch (dir) {
            case UP:
                return 2;
            case DOWN:
                return 3;
            case LEFT:
                return 1;
            case RIGHT:
                return 0;
        }
        return 0;
    }

    public static int getGhostOffset(GhostType ghostType) {
        switch (ghostType) {
            case INKY:
                return 16;
            case PINKY:
                return 8;
            case BLINKY:
                return 0;
            case CLYDE:
                return 24;
        }
        return 0;
    }
}
