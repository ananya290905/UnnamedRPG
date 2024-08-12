package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel panel;

    public CollisionChecker(GamePanel panel){
        this.panel = panel;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / panel.tileSize;
        int entityRightCol = entityRightWorldX / panel.tileSize;
        int entityTopRow = entityTopWorldY / panel.tileSize;
        int entityBottomRow = entityBottomWorldY / panel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / panel.tileSize;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / panel.tileSize;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX + entity.speed) / panel.tileSize;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) / panel.tileSize;
                tileNum1 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }


    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < panel.object.length; i++){
            if(panel.object[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                panel.object[i].solidArea.x = panel.object[i].worldX + panel.object[i].solidArea.x;
                panel.object[i].solidArea.y = panel.object[i].worldY + panel.object[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(panel.object[i].solidArea)){
                            if(panel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(panel.object[i].solidArea)){
                            if(panel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(panel.object[i].solidArea)){
                            if(panel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(panel.object[i].solidArea)){
                            if(panel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.object[i].solidArea.x = panel.object[i].solidAreaDefaultX;
                panel.object[i].solidArea.y = panel.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
