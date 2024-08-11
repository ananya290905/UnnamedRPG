package main;

import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;
    final public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 40;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize *  maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);

    TileManager tileManager = new TileManager(this);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();;
    }
    @Override
    public void run() {

        double drawInterval = (double) 1000000000/60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();

            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
