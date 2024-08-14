package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_MrG extends Entity{

    int actionCounter = 0;

    public NPC_MrG(GamePanel panel){
        super(panel);
        direction = "down";
        speed = 2;
        getImage();
    }

    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgup1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgdown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgleft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/npc/mrgright2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionCounter++;

        if(actionCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i < 25){
                direction = "up";
            }
            if(i >= 25 && i < 50){
                direction = "down";
            }

            if(i >= 50 && i < 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionCounter = 0;
        }

    }
}
