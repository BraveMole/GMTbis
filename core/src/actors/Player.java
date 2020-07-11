package actors;

import Utilities.AnimatedSprite;
import Utilities.BodyFactory;
import com.badlogic.gdx.graphics.Texture;
import Utilities.TextureLoader;

public class Player extends SuperActor{
    private boolean canJump;
    public Player(int x, int y,float width, float height){
        Texture text = TextureLoader.Player;
        this.sprite = new AnimatedSprite(text, x-width/2, y-height/2, -90, width, height);
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
        this.body= BodyFactory.createBody(x,y,width,height,"Dyna",true);
        BodyFactory.createSensor(0,-height/2,0.1f,0.1f,this.body);
    }
}
