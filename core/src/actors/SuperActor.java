package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static panic.game.GameClass.mainWorld;

public class SuperActor extends Actor {
    public AnimatedSprite sprite;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mustBeDrawn()) {
            this.sprite.animate(Gdx.graphics.getDeltaTime());
            this.sprite.setPosition(this.getX(), this.getY());
            this.sprite.setRotation(this.getRotation() - 90);
            this.sprite.draw(batch);
        }
    }

    private boolean mustBeDrawn() {
        return (mainWorld.getCamera().frustum.pointInFrustum(this.getX() - sprite.getWidth() / 2f, this.getY() - sprite.getHeight() / 2f, 0) | mainWorld.getCamera().frustum.pointInFrustum(this.getX() - sprite.getWidth() / 2f, this.getY() + sprite.getHeight() / 2f, 0) |
                mainWorld.getCamera().frustum.pointInFrustum(this.getX() + sprite.getWidth() / 2f, this.getY() - sprite.getHeight() / 2f, 0) | mainWorld.getCamera().frustum.pointInFrustum(this.getX() + sprite.getHeight() / 2f, this.getY() + sprite.getHeight() / 2f, 0));
    }

    public void setSprite(Animation animation) {
        this.sprite = new AnimatedSprite(animation, this.getX(), this.getY(), this.getRotation());
    }
}
