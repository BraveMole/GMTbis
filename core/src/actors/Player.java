package actors;

import com.badlogic.gdx.graphics.Texture;
import panic.game.TextureLoader;

public class Player extends SuperActor{
    public Player(float x, float y){
        Texture text = TextureLoader.Player;
        this.sprite = new AnimatedSprite(text, x, y, -90, text.getWidth(), text.getHeight());
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
