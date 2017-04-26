package game;

import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player1 {
    public int x, y;
    public int velocity;
    public int width, height;
    public int health;

    private int column = 0;
    private int row = 0;

    private SpriteSheet img;

    public static boolean goingUp1;
    public static boolean goingDown1;
    public static boolean goingLeft1;
    public static boolean goingRight1;
    public static boolean isKicking1;

    public Player1(int xCoord, int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.width = 70;
        this.height = 80;
        this.health = 50;
        this.velocity = 2;
        this.img = Assets.player1;

        goingUp1 = false;
        goingDown1 = false;
        goingLeft1 = false;
        goingRight1 = false;
        isKicking1 = false;
    }

    public int getHealth() {
        return this.health;
    }

    public void tick() {
        if (isKicking1) {
            this.row = 6;

            if (goingUp1) {
                this.y -= this.velocity;
            }
            if (goingDown1) {
                this.y += this.velocity;
            }
            if (goingLeft1) {
                this.x -= this.velocity;
            }
            if (goingRight1) {
                this.x += this.velocity;
            }

            this.column++;
            this.column %= 7;
        } else {
            this.row = 0;
            if (goingUp1) {
                this.y -= this.velocity;
            }
            if (goingDown1) {
                this.y += this.velocity;
            }
            if (goingLeft1) {
                this.x -= this.velocity;
            }
            if (goingRight1) {
                this.x += this.velocity;
            }
        }
    }

    public void render(Graphics g) {
        if (isKicking1) {
            g.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height), this.x, this.y, null);
        } else {
            g.drawImage(this.img.crop(this.column, this.row, this.width, this.height), this.x, this.y, null);
        }
    }
}
