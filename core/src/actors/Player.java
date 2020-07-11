package actors;

import Utilities.AnimatedSprite;
import Utilities.BodyFactory;
import com.badlogic.gdx.graphics.Texture;
import Utilities.TextureLoader;

public class Player extends SuperActor{
    public Player(int x, int y,float size){
        Texture text = TextureLoader.Player;
        width = (int)(text.getWidth()*size);
        height =  (int)(text.getHeight()*size);
        this.sprite = new AnimatedSprite(text, x-width/2, y-height/2, -90, width, height);
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
        this.body= BodyFactory.createBody(x,y,width,height,"Dyna",0f,10);
    }
}
