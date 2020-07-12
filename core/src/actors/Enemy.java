package actors;

import Utilities.AnimatedSprite;
import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import panic.game.GameClass;
import panic.game.TextureLoader;

public class Enemy extends SuperActor {
    private Polygon collisionPolygon;
    private float width;
    private float height;
    public Enemy(float x, float y) {
        Texture text = TextureLoader.Enemy;
        width = text.getWidth() * Settings.enemySize;
        height = text.getHeight()*Settings.enemySize;
        this.sprite = new AnimatedSprite(text, x, y, -90, width, height);
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
        float xdiff = p.getX() - this.getX();
        float ydiff = p.getY() - this.getY();
        float hypotenuse = (float) Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));

        this.setX(this.getX() + (xdiff * Settings.enemySpeed / hypotenuse));
        this.setY(this.getY() + (ydiff * Settings.enemySpeed / hypotenuse));
        collisionPolygon.setPosition(this.getX()-width/2,this.getY()-height/2);
        for (Projectile liveProjectile : GameClass.liveProjectiles) {
            if(Intersector.overlapConvexPolygons(liveProjectile.getCollisionPolygon(), this.getCollisionPolygon()))
            {
                GameClass.enemies.removeValue(this,false);
                GameClass.mainWorld.actors.removeActor(this);
            }
        }
        super.act(delta);
    }
}
