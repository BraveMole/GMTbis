package actors;

import Utilities.AnimatedSprite;
import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import panic.game.GameClass;
import panic.game.TextureLoader;

public class Enemy extends SuperActor {
    public Enemy(float x, float y) {
        Texture text = TextureLoader.Enemy;
        this.sprite = new AnimatedSprite(text, x, y, -90, text.getWidth(), text.getHeight());
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
    }

    @Override
    public void act(float delta) {
        Player p = GameClass.character;
        float xdiff = p.getX() - this.getX();
        float ydiff = p.getY() - this.getY();
        float hypotenuse = (float) Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));

        this.setX(this.getX() + (xdiff * Settings.enemySpeed / hypotenuse));
        this.setY(this.getY() + (ydiff * Settings.enemySpeed / hypotenuse));

        super.act(delta);
    }
}
