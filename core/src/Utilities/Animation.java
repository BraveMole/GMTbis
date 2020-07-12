package Utilities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import panic.game.TextureLoader;

public enum Animation {
    ENEMY_FLYING(1 / 4f, TextureLoader.flyingEnemySpriteSheet, 100* Settings.enemySize, 90* Settings.enemySize),
    ENEMY_FLYING_BIS(1 / 4f, TextureLoader.flyingEnemySpriteSheetBis, 100* Settings.enemySize, 90* Settings.enemySize),
    ENEMY_DYING(1/6f,TextureLoader.dyingEnemySpriteSheet,96* Settings.enemySize,93* Settings.enemySize),
    ENEMY_DYING_BIS(1/6f,TextureLoader.dyingEnemySpriteSheetBis,96* Settings.enemySize,93* Settings.enemySize),
    CHARACTER_STOPPING(1/6f,TextureLoader.characterStoping,128*Settings.playerSize,83*Settings.playerSize),
    CHARACTER_STOPPING_BIS(1/6f,TextureLoader.characterStopingBis,128*Settings.playerSize,83*Settings.playerSize),
    CHARACTER_RUNNING(1/5f,TextureLoader.characterRunning,128*Settings.playerSize,83*Settings.playerSize),
    CHARACTER_RUNNING_BIS(1/5f,TextureLoader.characterRunningBis,128*Settings.playerSize,83*Settings.playerSize),
    CHARACTER_STOPPED(1,TextureLoader.characterStopped,128*Settings.playerSize,83*Settings.playerSize),
    CHARACTER_STOPPED_BIS(1,TextureLoader.characterStoppedBis,128*Settings.playerSize,83*Settings.playerSize);
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

    public float getAnimationDuration(){
        return frameDuration*keyFrames.length;
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
