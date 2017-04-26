package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage bachground;
    public static SpriteSheet player1, player2;

    public static void init() {
        bachground = ImageLoader.loadImage("/textures/bckg.jpg");
        player1 = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        player2 = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
    }
}
