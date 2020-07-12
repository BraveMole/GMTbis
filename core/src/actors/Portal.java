package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import panic.game.GameClass;
import panic.game.TextureLoader;

public class Portal extends SuperActor {
    private Rectangle collisionRectangle;

    public Portal(float x, float y) {
        Texture text = TextureLoader.Portal;
        float width = text.getWidth();
        float height = text.getHeight();
        this.sprite = new AnimatedSprite(text, x - width / 2, y - height / 2, -90, width, height);
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
        collisionRectangle = new Rectangle(x-width/4,y-height/4,width/2,height/2);
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    @Override
    public void act(float delta) {
        Player p = GameClass.character;
        if (Intersector.overlaps(this.collisionRectangle, p.getCollisionRectangle())) {
            if (p.isOpen()){
                GameClass.endGame=true;
            }
        }

        super.act(delta);
    }
}