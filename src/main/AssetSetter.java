package main;

import entity.NPC_MrG;
import object.ObjectDoorL;
import object.ObjectDoorR;

public class AssetSetter {

    GamePanel panel;

    public AssetSetter(GamePanel panel){
        this.panel = panel;
    }

    public void setObject(){
        panel.object[0] = new ObjectDoorL();
        panel.object[0].worldX = panel.tileSize * 16;
        panel.object[0].worldY = panel.tileSize * 18;

        panel.object[1] = new ObjectDoorR();
        panel.object[1].worldX = panel.tileSize * 17;
        panel.object[1].worldY = panel.tileSize * 18;

    }

    public void setNPC(){
        panel.npc[0] = new NPC_MrG(panel);
        panel.npc[0].worldX = panel.tileSize * 20;
        panel.npc[0].worldY = panel.tileSize * 15;
    }
}
