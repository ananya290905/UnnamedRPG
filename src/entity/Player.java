package entity;

import main.GamePanel;
import main.KeyHandler;
import object.ObjectDoorL;
import object.ObjectDoorR;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Player extends Entity{

    public final int screenX;
    public final int screenY;

    GamePanel panel;
    KeyHandler keyHandler;

    List<SuperObject> openedDoors;
    public Player(GamePanel panel, KeyHandler keyHandler){
        this.keyHandler = keyHandler;
        this.panel = panel;

        screenX = panel.screenWidth/2 - (panel.tileSize /2);
        screenY = panel.screenHeight/2 - (panel.tileSize /2);

        solidArea = new Rectangle(1, 1, 40, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
        openedDoors = new ArrayList<>();
    }

    public void setDefaultValues(){
        worldX = panel.tileSize * 20;
        worldY = panel.tileSize * 20;

        speed = 10;
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
                direction = "up";
            } else if(keyHandler.downPressed){
                direction = "down";
            } else if(keyHandler.leftPressed){
                direction = "left";
            } else if(keyHandler.rightPressed){
                direction = "right";
            }

            collisionOn = false;
            panel.collisionChecker.checkTile(this);

            int objIndex = panel.collisionChecker.checkObject(this, true);

            if (objIndex == 999) {
                for (SuperObject door : openedDoors) {
                    door.visible = true;
                }
                openedDoors.clear(); // Clear the list after resetting doors
            }

            pickUpObject(objIndex);
            if(!collisionOn){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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

    public void pickUpObject(int i){
        if(i != 999){
            SuperObject so = panel.object[i];
            if(so.getClass() == ObjectDoorL.class || so.getClass() == ObjectDoorR.class){
                so.visible = false;
                openedDoors.add(so);
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

        g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);

    }
}
