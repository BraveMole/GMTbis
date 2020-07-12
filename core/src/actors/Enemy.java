package actors;

import Utilities.Animation;
import Utilities.Settings;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import panic.game.GameClass;

public class Enemy extends SuperActor {
    private Polygon collisionPolygon;
    private float width;
    private float height;
    private boolean goesToRight;
    private boolean isFlip;
    private boolean firstFlip;
    private boolean dead;
    private float timeToDie;

    public Enemy(float x, float y) {
        this.setSprite(Animation.ENEMY_FLYING);
        width = Animation.ENEMY_FLYING.getWidth();
        height = Animation.ENEMY_FLYING.getHeight();
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
        if (!dead) {
            Player p = GameClass.character;
            float xdiff = p.getX() - this.getX();
            float ydiff = p.getY() - this.getY();
            goesToRight = (xdiff < 0);
            float hypotenuse = (float) Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));

            this.setX(this.getX() + (xdiff * Settings.enemySpeed / hypotenuse));
            this.setY(this.getY() + (ydiff * Settings.enemySpeed / hypotenuse));
            collisionPolygon.setPosition(this.getX() - width / 2, this.getY() - height / 2);
            for (Projectile liveProjectile : GameClass.liveProjectiles) {
                if (Intersector.overlapConvexPolygons(liveProjectile.getCollisionPolygon(), this.getCollisionPolygon())) {
                    dead = true;
                    timeToDie = 0;
                    GameClass.enemies.removeValue(this,false);
                    if (isFlip) {
                        this.setSprite(Animation.ENEMY_DYING);
                    } else {
                        this.setSprite(Animation.ENEMY_DYING_BIS);
                    }
                }
        this.setX(this.getX() + (xdiff * Settings.enemySpeed / hypotenuse));
        this.setY(this.getY() + (ydiff * Settings.enemySpeed / hypotenuse));
        collisionPolygon.setPosition(this.getX()-width/2,this.getY()-height/2);
        Polygon placeHodler = new Polygon();
        for (Projectile liveProjectile : GameClass.liveProjectiles) {
            if(Intersector.overlapConvexPolygons(liveProjectile.getCollisionPolygon(), this.getCollisionPolygon())) {
                this.die(true);
            }
            if (firstFlip) {
                if (goesToRight) {
                    isFlip = true;
                    this.setSprite(Animation.ENEMY_FLYING);
                    firstFlip = false;
                }
            }
            if (isFlip && goesToRight && !dead) {
                this.setSprite(Animation.ENEMY_FLYING);
                isFlip = false;
            }
            if (!isFlip && !goesToRight && !dead) {
                this.setSprite(Animation.ENEMY_FLYING_BIS);
                isFlip = true;
            }
            super.act(delta);
        }
        else{
            timeToDie += Gdx.graphics.getDeltaTime();
            if (timeToDie>Animation.ENEMY_DYING.getAnimationDuration()-0.05f){
                GameClass.mainWorld.actors.removeActor(this);
            }
        }

    }

    public void die(boolean killed){
        if (killed) {
            GameClass.sm.enemykilled.play();
        }

        GameClass.enemies.removeValue(this,false);
        GameClass.mainWorld.actors.removeActor(this);
    }
}
