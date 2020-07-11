package actors;

import com.badlogic.gdx.graphics.Texture;
import panic.game.TextureLoader;

public class Key extends SuperActor {
    Texture[] textures = new Texture[2];
    Texture t;
    String name;

    public Key(float x, float y, Texture texton, Texture textoff, String s) {
        this.setX(x);
        this.setY(y);
        textures[0] = texton;
        textures[1] = textoff;

        t = texton;
        this.sprite = new AnimatedSprite(t, x, y, 0, t.getWidth(), t.getHeight());
        this.setRotation(90);
        name = s;
    }

    @Override
    public void act(float delta) {
        if (Player.controlsleft.contains(name)){
            t = textures[0];
        }
        else{
            t = textures[1];
        }
        this.sprite.setTexture(t);

        super.act(delta);
    }
}