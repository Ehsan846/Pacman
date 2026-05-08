package dk.sdu.imada.oop26.View;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import dk.sdu.imada.oop26.Model.Direction;
import dk.sdu.imada.oop26.Model.GhostType;

public class Sprites {
    public static final int DRAWSIZE = 40;
    public static final int SIZE = 16;
    private static final int OFFSET = 455;

    public static Sprite emptySprite;
    public static Sprite lives;

    public static Sprite[] pacman;
    public static Sprite[] pacmanDeath;

    public static Sprite[][] ghosts;
    public static Sprite[] ghostScared;
    public static Sprite[] ghostBlinking;
    public static Sprite[] ghostDeath;

    public static void loadSprites(Image sheet) {
        loadUISprites(sheet);
        
        loadPacmanSprites(sheet);

        loadGhostSprites(sheet);
    }

    public static Sprite[] getGhostSprites(GhostType ghostType) {
        switch (ghostType) {
            case INKY:
                return ghosts[2];
            case PINKY:
                return ghosts[1];
            case BLINKY:
                return ghosts[0];
            case CLYDE:
                return ghosts[3];
        }
        return null;
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

    private static void loadUISprites(Image sheet) {
        lives = new Sprite(sheet, OFFSET + 8 * SIZE + 1, SIZE);
        
        emptySprite = new Sprite(sheet, 0, 0, 1, 1, 1, 1);
    }

    private static void loadPacmanSprites(Image sheet) {
        final int PACMAN_SPRITE_COUNT = 4;
        pacman = new Sprite[PACMAN_SPRITE_COUNT * 4];
        for (int i = 0; i < 4; i++) {
            pacman[i * PACMAN_SPRITE_COUNT] = new Sprite(sheet, OFFSET, i * SIZE);
            pacman[i * PACMAN_SPRITE_COUNT + 1] = new Sprite(sheet, OFFSET + SIZE, i * SIZE);
            pacman[i * PACMAN_SPRITE_COUNT + 2] = new Sprite(sheet, OFFSET + 2 * SIZE, 0);
            pacman[i * PACMAN_SPRITE_COUNT + 3] = pacman[i * PACMAN_SPRITE_COUNT + 1];
        }

        pacmanDeath = new Sprite[11];
        for (int i = 0; i < 11; i++) {
            pacmanDeath[i] = new Sprite(sheet, OFFSET + (i + 3) * SIZE, 0);
        }
    }

    private static void loadGhostSprites(Image sheet) {
        ghosts = new Sprite[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ghosts[i][j] = new Sprite(sheet, OFFSET + j * SIZE, (i + 4) * SIZE);
            }
        }

        ghostScared = new Sprite[2];
        ghostBlinking = new Sprite[4];
        for (int i = 0; i < 2; i++) {
            ghostScared[i] = new Sprite(sheet, OFFSET + (i + 8) * SIZE, 4 * SIZE);
            ghostBlinking[i] = ghostScared[i];
        }

        for (int i = 2; i < 4; i++) {
            ghostBlinking[i] = new Sprite(sheet, OFFSET + (i + 8) * SIZE, 4 * SIZE);
        }

        ghostDeath = new Sprite[4];
        for (int i = 0; i < 4; i++) {
            ghostDeath[i] = new Sprite(sheet, OFFSET + (i + 8) * SIZE, 5 * SIZE);
        }
    }
}
