package dk.sdu.imada.oop26.View;

import dk.sdu.imada.oop26.Model.Direction;

public class Animator {
    private Sprite[] sprites;
    private double animationTime;
    private int spriteCount;
    private boolean looping = true;

    private int current = 0;
    private double timer = 0;

    public Animator() { }

    public void setSprites(Sprite[] sprites, boolean splits, double animationTime) {
        this.sprites = sprites;
        this.animationTime = animationTime;
        spriteCount = sprites.length;
        if (splits)
            spriteCount /= 4;
        current = 0;
        timer = 0;
    }

    public void update(double dt) {
        timer += dt;
        if (timer > animationTime) {
            timer -= animationTime;
            current++;
            if (looping && current >= spriteCount) {
                current = 0;
            }
        }
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public Sprite getDirectionalSprite(Direction dir) {
        if (sprites == null)
            return Sprites.emptySprite;
        
        int index = current;
        if (spriteCount != sprites.length)
            index += spriteCount * Sprites.getRotationOffset(dir);
        if (index < sprites.length)
            return sprites[index];
        return Sprites.emptySprite;
    }
}
