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
    private Polygon collisionPolygon;

    public Portal(float x, float y) {
        Texture text = TextureLoader.Portal;
        float width = text.getWidth();
        float height = text.getHeight();
        this.sprite = new AnimatedSprite(text, x - width / 2, y - height / 2, -90, width, height);
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
        collisionPolygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
    }

    public Polygon getCollisionPolygon() {
        return collisionPolygon;
    }

    @Override
    public void act(float delta) {
        Player p = GameClass.character;
        if (Intersector.overlaps(this.getCollisionPolygon().getBoundingRectangle(), p.getCollisionRectangle())) {
            if (p.open){
                // TODO Fix
            }
        }

        super.act(delta);
    }
}