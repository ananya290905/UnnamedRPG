package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    GamePanel panel;

    public KeyHandler(GamePanel panel){
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(panel.gameState == panel.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }

            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                panel.gameState = panel.pauseState;
            }
            

        }

        if(panel.gameState == panel.pauseState){
            if(code == KeyEvent.VK_ESCAPE){
                panel.gameState = panel.playState;
            }
        }


        if(panel.gameState == panel.dialogState){
            if(code == KeyEvent.VK_ENTER){
                panel.gameState = panel.playState;
            }

            if(code == KeyEvent.VK_SPACE){
                panel.npc[panel.npcIndex].speak();

            }

            if(code == KeyEvent.VK_1){
                panel.npc[panel.npcIndex].respondToPlayer(1);
            }
            if(code == KeyEvent.VK_2){
                panel.npc[panel.npcIndex].respondToPlayer(2);
            }



        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
