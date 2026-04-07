package dk.sdu.imada.oop26.View;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GhostType;

public class Sprites {
    public static Sprite[] pacmanSprites;
    public static Sprite[] pacmanDeathSprites;
    public static Sprite hpSprite;
    public static final int pacmanCount = 4;

    public static Sprite[] ghostSprites;

    public static void loadSprites(Image sheet) {
        pacmanSprites = new Sprite[pacmanCount * 4];
        for (int i = 0; i < 4; i++) {
            pacmanSprites[i * pacmanCount] = new Sprite(sheet, 455, i * 16, 16, 16, 40, 40);
            pacmanSprites[i * pacmanCount + 1] = new Sprite(sheet, 455 + 16, i * 16, 16, 16, 40, 40);
            pacmanSprites[i * pacmanCount + 2] = new Sprite(sheet, 455 + 32, 0, 16, 16, 40, 40);
            pacmanSprites[i * pacmanCount + 3] = pacmanSprites[i * pacmanCount + 1];
        }

        pacmanDeathSprites = new Sprite[11];
        for (int i = 0; i < 11; i++) {
            pacmanDeathSprites[i] = new Sprite(sheet, 455 + 48 + 16 * i, 0, 16, 16, 40, 40);
        }

        hpSprite = new Sprite(sheet, 456 + 128, 16, 16, 16, 40, 40);

        ghostSprites = new Sprite[32];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ghostSprites[i * 8 + j] = new Sprite(sheet, 455 + 16 * j, 64 + 16 * i, 16, 16, 40, 40);
            }
        }
    }

    public static int getRotationOffset(Direction dir) {
        switch (dir) {
            case DOWN:
                return 3;
            case LEFT:
                return 1;
            case UP:
                return 2;
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
