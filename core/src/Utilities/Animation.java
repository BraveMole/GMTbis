package Utilities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import panic.game.TextureLoader;

public enum Animation {
    ENEMY_FLYING(1 / 4f, TextureLoader.flyingEnemySpriteSheet, 100* Settings.enemySize, 90* Settings.enemySize),
    ENEMY_DYING(1/6f,TextureLoader.dyingEnemySpriteSheet,96* Settings.enemySize,93* Settings.enemySize);
    ;

    private float frameDuration;
    private TextureRegion[] keyFrames;
    private float width;
    private float height;

    Animation(float frameDuration, TextureRegion[] keyFrames, float width, float height) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
        this.width = width;
        this.height = height;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public TextureRegion[] getKeyFrames() {
        return keyFrames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
