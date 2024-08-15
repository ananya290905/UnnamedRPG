package main;

import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class UI {

    GamePanel panel;
    public boolean messageOn = false;

    int messageCounter = 0;
    Graphics2D g2;
    Font baloo;


    public String currentDialogue;

    public String message = "";
    public UI(GamePanel panel) throws IOException, FontFormatException {
        this.panel = panel;

        InputStream is = getClass().getResourceAsStream("/resources/font/Baloo-Regular.ttf");
        baloo = Font.createFont(Font.TRUETYPE_FONT, is);
    }

    public void draw(Graphics2D g2){
//        g2.setFont(baloo_40);
//        g2.setColor(Color.white);
//
//        g2.drawString("ITEMS", 25, 50);
//
//        if(messageOn){
//            g2.drawString(message, panel.tileSize/2, panel.tileSize*5);
//            messageCounter++;
//        }
//        if(messageCounter > 120){
//            messageCounter = 0;
//            messageOn = false;
//        }
        this.g2 = g2;
        g2.setFont(baloo);
        if(panel.gameState == panel.playState){

        }
        if(panel.gameState == panel.pauseState){
            drawPauseState();
        }
        if(panel.gameState == panel.dialogState){
            drawDialogueScreen();
        }
    }

    public void drawDialogueScreen(){
        int x = panel.tileSize * 2;
        int y = panel.tileSize / 2;
        int width = panel.screenWidth - panel.tileSize * 4;
        int height = panel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        Entity e = panel.npc[panel.npcIndex];

        if(panel.npc[panel.npcIndex].showOptions){
            drawOptions(x, 150 + y + panel.tileSize * 4, panel.screenWidth - panel.tileSize * 4, (panel.tileSize * 4)/2, e.response[0]);
            drawOptions(x, 150 + y + (panel.tileSize * 4) + (panel.tileSize * 4)/2, panel.screenWidth - panel.tileSize * 4, (panel.tileSize * 4)/2, e.response[1]);
        }

    }

    public void drawOptions(int x, int y, int width, int height, String option){
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(Color.white);

        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width - 10, height - 10, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        x += panel.tileSize;
        y += panel.tileSize;

        for(String line : option.split("\n")){
            g2.drawString(line, x, y);
            y += 30;
        }
    }


    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(Color.white);

        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width - 10, height - 10, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += panel.tileSize;
        y += panel.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void drawPauseState(){

        String text = "PAUSED";
        int x = getXForText(text);
        int y = panel.screenHeight / 2;
        g2.setColor(Color.white);

        g2.drawString(text, x, y);
    }

    private int getXForText(String text) {
        int len = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = panel.screenWidth/2 - len/2;
        return x;
    }

}
