package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel panel;
    KeyHandler keyHandler;

    public Player(GamePanel panel, KeyHandler keyHandler){
        this.keyHandler = keyHandler;
        this.panel = panel;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;

        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/right2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if(keyHandler.upPressed){
                y -= speed;
                direction = "up";
            } else if(keyHandler.downPressed){
                y += speed;
                direction = "down";
            } else if(keyHandler.leftPressed){
                x -= speed;
                direction = "left";
            } else if(keyHandler.rightPressed){
                x += speed;
                direction = "right";
            }

            spriteCounter ++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, null);

    }
}
