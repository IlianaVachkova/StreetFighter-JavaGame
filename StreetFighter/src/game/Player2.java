package game;

import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player2 {
    public int x, y;
    public int velocity;
    public int width, height;
    public int health;

    private int column = 0;
    private int row = 0;

    private SpriteSheet img;

    public static boolean goingUp2;
    public static boolean goingDown2;
    public static boolean goingLeft2;
    public static boolean goingRight2;
    public static boolean isKicking2;

    public Player2(int xCoord, int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.width = 70;
        this.height = 80;
        this.health = 50;
        this.velocity = 2;
        this.img= Assets.player2;

        goingUp2 = false;
        goingDown2 = false;
        goingLeft2 = false;
        goingRight2 = false;
        isKicking2 = false;
    }

    public int getHealth() {
        return this.health;
    }

    public void tick() {
        if (isKicking2) {
            this.row = 6;

            if (goingUp2) {
                this.y -= this.velocity;
            }
            if (goingDown2) {
                this.y += this.velocity;
            }
            if (goingLeft2) {
                this.x -= this.velocity;
            }
            if (goingRight2) {
                this.x += this.velocity;
            }

            this.column++;
            this.column %= 7;
        } else {
            this.row = 0;
            if (goingUp2) {
                this.y -= this.velocity;
            }
            if (goingDown2) {
                this.y += this.velocity;
            }
            if (goingLeft2) {
                this.x -= this.velocity;
            }
            if (goingRight2) {
                this.x += this.velocity;
            }
        }
    }

    public void render(Graphics g) {
        if (isKicking2) {
            g.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height), this.x, this.y, null);
        } else {
            g.drawImage(this.img.crop(this.column, this.row, this.width, this.height), this.x, this.y, null);
        }
    }
}
