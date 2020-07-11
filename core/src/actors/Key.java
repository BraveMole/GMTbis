package actors;

import com.badlogic.gdx.graphics.Texture;
import panic.game.TextureLoader;

public class Key extends SuperActor{
    Texture[] textures;
    public Key(float x, float y, Texture[] text){
        this.setX(x);
        this.setY(y);
        textures = text;
    }
}
