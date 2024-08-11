package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel panel;

    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GamePanel panel){
        this.panel = panel;
        tile = new Tile[10];

        getTileImage();
        mapTileNum = new int[panel.maxWorldCol][panel.maxWorldRow];
        loadMap("/resources/map/map1");

    }

    public void loadMap(String path){
        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < panel.maxWorldCol && row < panel.maxWorldRow){
                String line = reader.readLine();

                while(col < panel.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == panel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/grass2.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_left.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_right.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/tree.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
      int worldCol = 0;
      int worldRow = 0;

      while(worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow){

          int tileNum = mapTileNum[worldCol][worldRow];

          int worldX = worldCol * panel.tileSize;
          int worldY = worldRow * panel.tileSize;

          int screenX = worldX - panel.player.worldX + panel.player.screenX;
          int screenY = worldY - panel.player.worldY + panel.player.screenY;

          if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX && worldX - panel.tileSize< panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY && worldY - panel.tileSize< panel.player.worldY + panel.player.screenY){
              g2.drawImage(tile[tileNum].image, screenX, screenY, panel.tileSize, panel.tileSize, null);
          }
          worldCol ++;

          if(worldCol == panel.maxWorldCol){
              worldCol = 0;
              worldRow ++;
          }
      }
    }
}

