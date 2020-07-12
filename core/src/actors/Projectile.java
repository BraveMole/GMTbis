package actors;

import Utilities.AnimatedSprite;
import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import panic.game.TextureLoader;

public class Projectile extends SuperActor {
    private float directionAngle;
    public Projectile(float x, float y, float directionAngle){
        this.setX(x);this.setY(y);
        Texture text = TextureLoader.Projectile;
        this.directionAngle = directionAngle;
        this.setRotation((float)Math.toDegrees(directionAngle)+90);
        this.sprite = new AnimatedSprite(text, x, y, 20, text.getWidth(), text.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setX((float)(this.getX()+delta* Settings.projectileSpeed*Math.cos(directionAngle)));
        this.setY((float)(this.getY()+delta* Settings.projectileSpeed*Math.sin(directionAngle)));
    }
}
