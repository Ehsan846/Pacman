// package dk.sdu.imada.oop26.View;

// import dk.sdu.imada.oop26.Model.Direction;

// public class Animator {
//     private Sprite[] sprites;
//     protected final double animationTime;
//     protected final int spriteCount;

//     protected int current = 0;
//     protected double timer = 0;

//     public Animator(Sprite[] sprites, double animationTime, int splits) {
//         if (sprites.length % splits != 0) {
//             System.out.println("WARNING: Sprite length: " + sprites.length + " cannot be split evenly with " + splits + " splits.");
//         }

//         this.sprites = sprites;
//         this.animationTime = animationTime;
//         spriteCount = sprites.length / splits;
//     }

//     public void update(double dt) {
//         timer += dt;
//         if (timer > animationTime) {
//             timer -= animationTime;
//             current++;
//             if (current >= spriteCount) {
//                 current = 0;
//             }
//         }
//     }

//     public Sprite getDirectionalSprite(Direction dir) {
//         final int rotationOffset = spriteCount * Sprites.getRotationOffset(dir);
//         return sprites[current + rotationOffset];
//     }
// }
