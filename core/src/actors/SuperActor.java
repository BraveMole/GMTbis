package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import Utilities.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static panic.game.MainClass.staticMainClass;

public class SuperActor extends Actor {
    public AnimatedSprite sprite;
    public Body body;
    public int width;
    public int height;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mustBeDrawn()) {
            this.sprite.animate(Gdx.graphics.getDeltaTime());
            this.sprite.setPosition(this.getX(), this.getY());
            this.sprite.setRotation(this.getRotation() - 90);
            this.sprite.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(this.body.getPosition().x,this.body.getPosition().y);
        //We add friction here
        this.body.setLinearVelocity(this.body.getLinearVelocity().x* Settings.friction,this.body.getLinearVelocity().y);
    }

    private boolean mustBeDrawn() {
        return (staticMainClass.getCamera().frustum.pointInFrustum(this.getX() - sprite.getWidth() / 2f, this.getY() - sprite.getHeight() / 2f, 0) | staticMainClass.getCamera().frustum.pointInFrustum(this.getX() - sprite.getWidth() / 2f, this.getY() + sprite.getHeight() / 2f, 0) |
                staticMainClass.getCamera().frustum.pointInFrustum(this.getX() + sprite.getWidth() / 2f, this.getY() - sprite.getHeight() / 2f, 0) | staticMainClass.getCamera().frustum.pointInFrustum(this.getX() + sprite.getHeight() / 2f, this.getY() + sprite.getHeight() / 2f, 0));
    }

    public void setSprite(Animation animation) {
        this.sprite = new AnimatedSprite(animation, this.getX()-width/2, this.getY()+height/2, this.getRotation());
    }
}
