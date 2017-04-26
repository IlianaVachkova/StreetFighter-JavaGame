package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    private Display display;

    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet sh;

    private State gameState;
    private State menuState;
    private State settingsState;

    public static Player1 player1;
    public static Player2 player2;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(this.title, this.width, this.height);
        img = ImageLoader.loadImage("/textures/bckg.jpg");
        sh = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));

        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        gameState = new GameState();
        menuState = new MenuState();
        settingsState = new SettingsState();
        StateManager.setState(gameState);

        player1 = new Player1(600, 300);
        player2 = new Player2(100, 200);
    }


    private void tick() {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
        player1.tick();
        player2.tick();

        if (kickingIntersection(player1, player2)) {
            player1.health -= 20;
            player2.health += 20;

            if (player1.health <= 0) {
                System.out.println("You died");
                stop();
            } else {
                System.out.printf("Player 1 health %s", player1.health);
                System.out.println();
            }

            if (player2.health <= 0) {
                System.out.println("You died");
                stop();
            } else {
                System.out.printf("Player 2 health %s", player2.health);
                System.out.println();
            }
        } else if (testIntersection(player1, player2)) {

            player1.health -= 10;
            player2.health += 10;

            if (player1.health <= 0) {
                System.out.println("You died");
                stop();
            } else {
                System.out.printf("Player 1 health %s", player1.health);
                System.out.println();
            }

            if (player2.health <= 0) {
                System.out.println("You died");
                stop();
            } else {
                System.out.printf("Player 2 health %s", player2.health);
                System.out.println();
            }
        }
    }

    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, this.width, this.height);

        g.drawImage(img, 0, 0, this.width, this.height, null);

        player1.render(g);

        player2.render(g);

        if (StateManager.getState() != null) {
            StateManager.getState().render(this.g);
        }

        bs.show();
        g.dispose();
    }

    public static boolean testIntersection(Player1 player1, Player2 player2) {
        if ((player1.x == player2.x) || (player2.x == player1.x)) {
            return true;
        }
        return false;
    }

    public static boolean kickingIntersection(Player1 player1, Player2 player2) {
        if ((player1.x == player2.x) || (player2.x == player1.x) && (player1.isKicking1 || player2.isKicking2)) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        init();

        int fps = 15;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
