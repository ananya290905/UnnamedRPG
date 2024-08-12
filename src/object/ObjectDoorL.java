package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoorL extends SuperObject{

    public ObjectDoorL(){
        name = "door_left";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/door_left.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
