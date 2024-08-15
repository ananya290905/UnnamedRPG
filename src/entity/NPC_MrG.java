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
        setDialogue();
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

        if(actionCounter == 60){
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

    public void setDialogue(){
        dialogues[0] = "Psst! Hey folk! What are you \ndoing? You are not supposed to be\nhere!";
        dialogues[1] = "Listen, do you think you can help\nme? If I decorate my class\n maybe my students will return!";
        dialogues[2] = "All my supplies and furniture\nwas thrown in the central river.";
        dialogues[3] = "I reckon if you get a fishing\nrod, you will be sure to find\nsomething of mine.";
        dialogues[4] = "If you go south-east of this town,\nfollowing the pathway you will\nfind a barkery";


        if(dialogueIndex == 0){
            showOptions = true;
            response[0] = "1 : Who are you?";
            response[1] = "2 : Why?";
            replyIndex = 0;
        } else if(dialogueIndex == 1){
            showOptions = true;
            response[0] = "1 : That will not work.";
            response[1] = "2 : What do I have to do?";
            replyIndex = 1;
        } else if(dialogueIndex == 4){
            showOptions = true;
            response[0] = "1 : What is a barkery?";
            response[1] = "2 : Why would they have a fishing\n    rod?";
            replyIndex = 2;
        }

    }

    public void speak(){
        if(dialogueIndex >= dialogues.length || dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        panel.ui.currentDialogue = dialogues[dialogueIndex];
        if (dialogueIndex == 0 || dialogueIndex == 1 || dialogueIndex == 4) {
            setDialogue();
        }
        dialogueIndex++;
    }

    public void respondToPlayer(int selected){
        switch (replyIndex){
            case 0 :
                setReply(selected, "I am Mr.G, the local school\nteacher. My students have\ndisappeared under weird\ncircumstances...",
                        "This is my old classroom.\nThe children.. they are gone now..\n I like to come here to sulk.");
                break;
            case 1 :
                setReply(selected, "Trying being more positive, folk!\nHelp a poor old mather out.", "Great to have you on board!");
                break;
            case 2 :
                setReply(selected, "It is a bar and bakery silly!", "Kate is the co-owner and the\ngadget person of this town\nShe always has something useful\nfor all of us. [PRESS ENTER TO EXIT]");
                break;
        }
        showOptions = false;
    }

    private void setReply(int selected, String a, String b) {
        if(selected == 1){
            panel.ui.currentDialogue = a;
        } else if (selected == 2){
            panel.ui.currentDialogue = b;
        }
    }


}
