package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.player1.goingUp1 = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player1.goingDown1 = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player1.goingLeft1 = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player1.goingRight1 = true;
        }
        if (keyCode == KeyEvent.VK_L) {
            Game.player1.isKicking1 = true;
        }
        if (keyCode == KeyEvent.VK_2) {
            Game.player2.goingUp2 = true;
        }
        if (keyCode == KeyEvent.VK_3) {
            Game.player2.goingDown2 = true;
        }
        if (keyCode == KeyEvent.VK_1) {
            Game.player2.goingLeft2 = true;
        }
        if (keyCode == KeyEvent.VK_4) {
            Game.player2.goingRight2 = true;
        }
        if (keyCode == KeyEvent.VK_W) {
            Game.player2.isKicking2 = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.player1.goingUp1 = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player1.goingDown1 = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player1.goingLeft1 = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player1.goingRight1 = false;
        }
        if (keyCode == KeyEvent.VK_L) {
            Game.player1.isKicking1 = false;
        }
        if (keyCode == KeyEvent.VK_2) {
            Game.player2.goingUp2 = false;
        }
        if (keyCode == KeyEvent.VK_3) {
            Game.player2.goingDown2 = false;
        }
        if (keyCode == KeyEvent.VK_1) {
            Game.player2.goingLeft2 = false;
        }
        if (keyCode == KeyEvent.VK_4) {
            Game.player2.goingRight2 = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            Game.player2.isKicking2 = false;
        }
    }
}
