package actors;

import Utilities.AnimatedSprite;
import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import panic.game.TextureLoader;

public class Projectile extends SuperActor {
    private float directionAngle;
    private Polygon collisionPolygon;
    private float width;
    private float height;
    public Projectile(float x, float y, float directionAngle){
        this.setX(x);this.setY(y);
        Texture text = TextureLoader.Projectile;
        this.directionAngle = directionAngle;
        this.setRotation((float)Math.toDegrees(directionAngle)+90);
        width = text.getWidth() * Settings.enemySize;
        height = text.getHeight()*Settings.enemySize;
        this.sprite = new AnimatedSprite(text, x, y, 0, width, height);
        collisionPolygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
        collisionPolygon.setOrigin(width/2,height/2);
        collisionPolygon.setPosition(x,y);
    }

    public Polygon getCollisionPolygon() {
        return collisionPolygon;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setX((float)(this.getX()+delta* Settings.projectileSpeed*Math.cos(directionAngle)));
        this.setY((float)(this.getY()+delta* Settings.projectileSpeed*Math.sin(directionAngle)));
        collisionPolygon.setPosition(this.getX(),this.getY());
    }
}
