package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject {

    public BufferedImage image;

    public String name;

    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public boolean interacted = false;

    public boolean visible = true;

    public void draw(Graphics2D g2, GamePanel panel){
        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX && worldX - panel.tileSize< panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY && worldY - panel.tileSize< panel.player.worldY + panel.player.screenY){
            g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }
    }
}
