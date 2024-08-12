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

    public Tile[] tile;

    public int mapTileNum[][];

    public TileManager(GamePanel panel){
        this.panel = panel;
        tile = new Tile[25];

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
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/bush.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/bush.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/floor.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/grass.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/grass2.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_bl.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_bottom.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_br.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_left.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_right.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_tl.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_top.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/path_tr.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/plain_tile.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/tree_bl.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/tree_br.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/tree_tl.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/tree_tr.png"));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/wall.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/wall2.png"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/resources/tile/water.png"));
            tile[20].collision = true;

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

