package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoorR extends SuperObject{

    public ObjectDoorR(){
        name = "door_right";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/door_right.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
