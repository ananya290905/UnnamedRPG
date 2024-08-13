package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel panel;
    Font baloo_40;
    public boolean messageOn = false;

    int messageCounter = 0;

    public String message = "";
    public UI(GamePanel panel){
        this.panel = panel;
        baloo_40 = new Font("Baloo Bhaijaan", Font.BOLD, 40);
    }

    public void draw(Graphics2D g2){
        g2.setFont(baloo_40);
        g2.setColor(Color.white);

        g2.drawString("ITEMS", 25, 50);

        if(messageOn){
            g2.drawString(message, panel.tileSize/2, panel.tileSize*5);
            messageCounter++;
        }
        if(messageCounter > 120){
            messageCounter = 0;
            messageOn = false;
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

}
